package kz.krg.taskplanner.service.impl;

import kz.krg.taskplanner.model.TaskStatus;
import kz.krg.taskplanner.model.dto.ReportHeaderDto;
import kz.krg.taskplanner.model.dto.TaskDetailedDto;
import kz.krg.taskplanner.service.ReportService;
import kz.krg.taskplanner.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final TaskService taskService;

    @Override
    public byte[] getTaskReport() {
        List<?> tasks = taskService.listTasksByStatus(null).stream()
                .map(task -> {
                    List<TaskStatus> taskStatuses = task.getStatuses();
                    taskStatuses.sort(Comparator.comparingLong(TaskStatus::getId));
                    Optional<TaskStatus> startStatus = taskStatuses.stream()
                            .filter(taskStatus -> taskStatus.getStatus().getId() == 15)
                            .findFirst();
                    Optional<TaskStatus> endStatus = taskStatuses.stream()
                            .filter(taskStatus -> taskStatus.getStatus().getId() == 14)
                            .reduce((first, second) -> second);
                    Date startDate = startStatus.map(TaskStatus::getCreated).orElse(null);
                    Date endDate = endStatus.map(TaskStatus::getCreated).orElse(null);
                    String workTime = getWorkTime(startDate, endDate);

                    return new TaskDetailedDto(
                            task.getCreated(),
                            task.getType().getType(),
                            task.getClient().getFio(),
                            task.getClient().getPhoneNumber(),
                            task.getClient().getEmail(),
                            task.getPrice(),
                            task.getComment(),
                            task.getWorker().getName(),
                            startDate,
                            endDate,
                            workTime
                    );
                }).toList();

        List<ReportHeaderDto> headers = List.of(
                new ReportHeaderDto("Дата создания", "createDate", "date"),
                new ReportHeaderDto("Тип работ", "workType", "string"),
                new ReportHeaderDto("ФИО клиента", "clientFio", "string"),
                new ReportHeaderDto("Номер телефона клиента", "clientPhoneNumber", "string"),
                new ReportHeaderDto("Email клиента", "clientEmail", "string"),
                new ReportHeaderDto("Цена", "price", "numeric"),
                new ReportHeaderDto("Комментарий", "comment", "string"),
                new ReportHeaderDto("ФИО сотрудника", "workerFio", "string"),
                new ReportHeaderDto("Взято в работу", "startDate", "date"),
                new ReportHeaderDto("Исполнено", "endDate", "date"),
                new ReportHeaderDto("Время работ", "workTime", "string"));

        return this.generateExcel(headers, tasks);
    }

    private byte[] generateExcel(List<ReportHeaderDto> headers, List<?> data){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SXSSFWorkbook book = new SXSSFWorkbook();

        try (os; book) {
            SXSSFSheet main = book.createSheet("Отчет");
            int rowNum = 0;
            int colNum = 0;
            SXSSFRow headerRow = main.createRow(rowNum++);
            for (ReportHeaderDto header : headers) {
                addCell(headerRow, header.getValue(), colNum++);
                main.setColumnWidth(colNum, 25 * 256);
            }
            cellCreator(
                    main,
                    rowNum,
                    data,
                    headers
            );
            book.write(os);
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SXSSFCell addCell(SXSSFRow row, String value, int colNum) {
        SXSSFCell cell = row.createCell(colNum);
        cell.setCellValue(value == null || "null".equals(value) ? "0" : value);
        return cell;
    }

    private void cellCreator(
            SXSSFSheet main,
            int rowNum,
            List<?> list,
            List<ReportHeaderDto> headers
    ) {
        for (Object o : list) {
            int colNum = 0;
            SXSSFRow row = main.createRow(rowNum++);
            try {
                for (ReportHeaderDto header : headers) {
                    Field privateField = o.getClass().getDeclaredField(header.getKey());
                    privateField.setAccessible(true);
                    String value = String.valueOf(privateField.get(o));
                    if (ObjectUtils.isEmpty(value) || value.equals("null")) {
                        addCell(row, "", colNum++);
                    } else if ("date".equals(header.getType())) {
                        addCell(row, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                                        .parse(value)), colNum++);
                    } else {
                        SXSSFCell cell = addCell(row, value, colNum++);
                        if ("numeric".equals(header.getType()) && isNumeric(value)) {
                            applyNumericFormat(
                                    main.getWorkbook(),
                                    cell,
                                    Double.valueOf(value)
                            );
                        }
                    }
                }
            } catch (Exception e) {
                addCell(row, "", colNum);
            }
        }
    }

    private boolean isNumeric(String s) {
        try {
            Double.valueOf(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private void applyNumericFormat(
            Workbook outWorkbook,
            Cell cell,
            Double value
    ) {
        CellStyle style = outWorkbook.createCellStyle();
        DataFormat format = outWorkbook.createDataFormat();
        style.setDataFormat(format.getFormat("0.00"));
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private String getWorkTime(Date start, Date end) {
        if (start == null || end == null) return null;

        StringBuilder workTime = new StringBuilder();

        long timeIn = start.getTime();
        long timeOut = end.getTime();
        long timeDifference = timeOut - timeIn;
        long daysDifference = TimeUnit.MILLISECONDS.toDays(timeDifference);
        if (daysDifference > 0) {
            workTime.append(daysDifference).append("д ");
        }

        long hoursDifference = TimeUnit.MILLISECONDS.toHours(timeDifference) % 24;
        if (hoursDifference > 0 || !workTime.isEmpty()) {
            workTime.append(hoursDifference).append("ч ");
        }

        long minutesDifference = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60;
        if (minutesDifference > 0 || !workTime.isEmpty()) {
            workTime.append(minutesDifference).append("м ");
        }

        return workTime.toString().trim();
    }
}
