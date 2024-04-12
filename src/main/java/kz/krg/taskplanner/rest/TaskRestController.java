package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Task;
import kz.krg.taskplanner.model.dto.TaskDto;
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
    public List<Task> getTasks(@RequestParam(required = false) Long statusId) {
        return taskService.listTasksByStatus(statusId);
    }

    @PostMapping
    public Task saveTask(@RequestBody TaskDto task) {
        return taskService.save(task);
    }
}
