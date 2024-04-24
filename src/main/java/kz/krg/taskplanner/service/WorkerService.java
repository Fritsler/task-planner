package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Worker;
import kz.krg.taskplanner.model.dto.WorkerDto;

import java.util.List;

public interface WorkerService {
    List<Worker> listWorkers();
    Worker save(WorkerDto worker);

    Worker getWorker(Long id);

    void delete(Long id);
}
