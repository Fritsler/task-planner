package kz.krg.taskplanner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskDetailedDto {
    private Date createDate;
    private String workType;
    private String clientFio;
    private String clientPhoneNumber;
    private String clientEmail;
    private Integer price;
    private String comment;
    private String workerFio;
    private Date startDate;
    private Date endDate;
    private String workTime;
}
