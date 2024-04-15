package kz.krg.taskplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table
@Data
@EntityListeners(AuditingEntityListener.class)
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_taskStatus")
    @SequenceGenerator(name = "sequenceGenerator_taskStatus",
            sequenceName = "seq_task_status", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Task task;
    @ManyToOne
    private Status status;
    @CreatedDate
    private Date created;
}
