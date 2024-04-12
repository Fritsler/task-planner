package kz.krg.taskplanner.repository;

import kz.krg.taskplanner.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    List<TaskStatus> findAllByStatusId(Long statusId);
}
