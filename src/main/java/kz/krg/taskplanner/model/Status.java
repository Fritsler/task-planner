package kz.krg.taskplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "sequenceGenerator_status")
    @SequenceGenerator(name = "sequenceGenerator_status",
            sequenceName = "seq_worker", allocationSize = 1)
    private Long id;
    private String status;
}
