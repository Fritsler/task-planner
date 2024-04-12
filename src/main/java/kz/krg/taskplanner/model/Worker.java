package kz.krg.taskplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    @OneToMany(mappedBy = "worker")
    private List<Task> tasks;
}
