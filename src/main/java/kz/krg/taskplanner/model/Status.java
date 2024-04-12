package kz.krg.taskplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Status {
    @Id
    private Long id;
    private String status;
}
