package kz.krg.taskplanner.model.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private Long workerId;
    private Long typeId;
    private ClientDto client;
    private Long statusId;
    private String comment;
    private Integer price;
}
