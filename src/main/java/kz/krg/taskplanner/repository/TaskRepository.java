package kz.krg.taskplanner.repository;

import kz.krg.taskplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
