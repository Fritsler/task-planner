package kz.krg.taskplanner.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_status")
    @SequenceGenerator(name = "sequenceGenerator_status",
            sequenceName = "seq_status", allocationSize = 1)
    private Long id;
    private String status;
    @OneToMany(mappedBy = "status")
    @JsonBackReference
    private List<Task> tasks;
}
