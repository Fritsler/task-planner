package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    List<Task> listTasksByStatus(Long statusId);
}
