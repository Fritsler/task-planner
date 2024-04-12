package kz.krg.taskplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Worker {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "worker")
    private List<Task> tasks;
}
