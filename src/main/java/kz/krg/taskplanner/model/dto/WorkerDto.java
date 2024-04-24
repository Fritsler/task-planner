package kz.krg.taskplanner.model.dto;

import lombok.Data;

@Data
public class WorkerDto {
    private Long id;
    private String name;
    private Boolean deleted;
}
