package kz.krg.taskplanner.model.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Long id;
    private String fio;
    private String phoneNumber;
    private String email;
}
