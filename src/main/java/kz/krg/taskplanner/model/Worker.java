package kz.krg.taskplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_worker")
    @SequenceGenerator(name = "sequenceGenerator_worker",
            sequenceName = "seq_worker", allocationSize = 1)
    private Long id;
    private String name;
    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;
    @OneToMany(mappedBy = "worker")
    @JsonBackReference
    private List<Task> tasks;
}
