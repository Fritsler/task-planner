package kz.krg.taskplanner.repository;

import kz.krg.taskplanner.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findAllByDeletedIsFalse();
}
