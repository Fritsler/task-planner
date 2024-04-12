package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Worker;
import kz.krg.taskplanner.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/worker")
@RequiredArgsConstructor
public class WorkerRestController {
    private final WorkerService workerService;

    @GetMapping("/list")
    public List<Worker> getWorkers() {
        return workerService.listWorkers();
    }

    @PostMapping
    public Worker saveWorker(@RequestBody Worker worker) {
        return workerService.save(worker);
    }
}
