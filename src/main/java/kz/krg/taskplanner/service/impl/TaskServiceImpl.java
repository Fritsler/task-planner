package kz.krg.taskplanner.service.impl;

import kz.krg.taskplanner.model.Task;
import kz.krg.taskplanner.model.TaskStatus;
import kz.krg.taskplanner.repository.TaskRepository;
import kz.krg.taskplanner.repository.TaskStatusRepository;
import kz.krg.taskplanner.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasksByStatus(Long statusId) {
        return taskStatusRepository.findAllByStatusId(statusId).stream()
                .map(TaskStatus::getTask)
                .collect(Collectors.toList());
    }
}
