package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Task;
import kz.krg.taskplanner.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/task")
@RequiredArgsConstructor
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping("/list")
    public List<Task> getTasksByStatus(@RequestParam Long statusId) {
        return taskService.listTasksByStatus(statusId);
    }

    @PostMapping
    public Task getTasksByStatus(@RequestBody Task task) {
        return taskService.save(task);
    }
}
