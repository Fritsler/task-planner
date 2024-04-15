package kz.krg.taskplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_task")
    @SequenceGenerator(name = "sequenceGenerator_task",
            sequenceName = "seq_task", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JsonManagedReference
    private Worker worker;
    @ManyToOne
    @JsonManagedReference
    private Type type;
    @ManyToOne
    @JsonManagedReference
    private Client client;
    @ManyToOne
    @JsonManagedReference
    private Status status;
    @OneToMany(mappedBy = "task")
    @JsonManagedReference
    private List<TaskStatus> statuses;
    private String comment;
    private Integer price;
    @CreatedDate
    private Date created;
}
