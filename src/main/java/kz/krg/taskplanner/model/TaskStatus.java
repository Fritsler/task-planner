package kz.krg.taskplanner.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table
@Data
public class TaskStatus {
    @Id
    private Long id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Status status;
    @CreatedDate
    private Date created;
}
