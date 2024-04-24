package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Worker;
import kz.krg.taskplanner.model.dto.ResponseDto;
import kz.krg.taskplanner.model.dto.WorkerDto;
import kz.krg.taskplanner.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Worker saveWorker(@RequestBody WorkerDto worker) {
        return workerService.save(worker);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteWorker(@PathVariable Long id) {
        try {
            workerService.delete(id);
            return new ResponseDto("success", null);
        } catch (Exception ex) {
            return new ResponseDto("error", ex.getMessage());
        }
    }
}
