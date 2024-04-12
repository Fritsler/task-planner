package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Task;
import kz.krg.taskplanner.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    Task save(TaskDto task);
    List<Task> listTasksByStatus(Long statusId);
}
