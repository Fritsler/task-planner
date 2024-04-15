package kz.krg.taskplanner.service.impl;

import kz.krg.taskplanner.model.Client;
import kz.krg.taskplanner.model.Status;
import kz.krg.taskplanner.model.Task;
import kz.krg.taskplanner.model.TaskStatus;
import kz.krg.taskplanner.model.Type;
import kz.krg.taskplanner.model.Worker;
import kz.krg.taskplanner.model.dto.TaskDto;
import kz.krg.taskplanner.repository.TaskRepository;
import kz.krg.taskplanner.repository.TaskStatusRepository;
import kz.krg.taskplanner.service.ClientService;
import kz.krg.taskplanner.service.StatusService;
import kz.krg.taskplanner.service.TaskService;
import kz.krg.taskplanner.service.TypeService;
import kz.krg.taskplanner.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final ClientService clientService;
    private final TypeService typeService;
    private final WorkerService workerService;
    private final StatusService statusService;

    @Override
    public Task save(TaskDto taskDto) {
        Task task = new Task();
        if (taskDto.getId() != null) {
            task = taskRepository.findById(taskDto.getId()).orElse(new Task());
        }
        task.setPrice(taskDto.getPrice());
        task.setComment(taskDto.getComment());

        if (taskDto.getWorkerId() != null) {
            Worker worker = workerService.getWorker(taskDto.getWorkerId());
            task.setWorker(worker);
        }
        Status status = null;
        if (taskDto.getStatusId() != null) {
            status = statusService.getStatus(taskDto.getStatusId());
            task.setStatus(status);
        }
        if (taskDto.getTypeId() != null) {
            Type type = typeService.getType(taskDto.getTypeId());
            task.setType(type);
        }
        if (taskDto.getClient() != null) {
            Client client = new Client();
            client.setId(taskDto.getClient().getId());
            client.setEmail(taskDto.getClient().getEmail());
            client.setFio(taskDto.getClient().getFio());
            client.setPhoneNumber(taskDto.getClient().getPhoneNumber());
            client = clientService.save(client);

            task.setClient(client);
        }

        task = taskRepository.save(task);

        if (status != null) {
            if (!status.equals(task.getStatus())) {
                TaskStatus taskStatus = new TaskStatus();
                taskStatus.setStatus(status);
                taskStatus.setTask(task);
                taskStatusRepository.save(taskStatus);
            }
        }

        return task;
    }

    @Override
    public List<Task> listTasksByStatus(Long statusId) {
        if (statusId == null) {
            return taskRepository.findAll();
        } else {
            return taskRepository.findAllByStatusId(statusId);
        }
    }
}
