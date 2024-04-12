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
public class Client {
    @Id
    private Long id;
    private String fio;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "client")
    private List<Task> tasks;
}
