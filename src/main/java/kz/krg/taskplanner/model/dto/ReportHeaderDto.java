package kz.krg.taskplanner.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportHeaderDto {
    private String value;
    private String key;
    private String type;
}
