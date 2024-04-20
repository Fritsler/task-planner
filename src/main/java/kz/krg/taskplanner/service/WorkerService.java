package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> listWorkers();
    Worker save(Worker worker);

    Worker getWorker(Long id);

    void delete(Long id);
}
