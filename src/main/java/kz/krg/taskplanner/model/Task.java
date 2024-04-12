package kz.krg.taskplanner.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class Task {
    @Id
    private Long id;
    @ManyToOne
    private Worker worker;
    @ManyToOne
    private Type type;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Status status;
    @OneToMany(mappedBy = "task")
    private List<TaskStatus> taskStatuses;
    private String comment;
    private Integer price;
    @CreatedDate
    private Date created;
}
