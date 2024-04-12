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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_taskStatus")
    @SequenceGenerator(name = "sequenceGenerator_taskStatus",
            sequenceName = "seq_worker", allocationSize = 1)
    private Long id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private Status status;
    @CreatedDate
    private Date created;
}
