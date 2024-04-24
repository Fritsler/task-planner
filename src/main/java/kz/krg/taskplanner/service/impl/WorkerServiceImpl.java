package kz.krg.taskplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.krg.taskplanner.model.Worker;
import kz.krg.taskplanner.model.dto.WorkerDto;
import kz.krg.taskplanner.repository.WorkerRepository;
import kz.krg.taskplanner.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    @Override
    public List<Worker> listWorkers() {
        return workerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Worker save(WorkerDto worker) {
        Worker workerToSave;
        if (worker.getId() == null) {
            workerToSave = new Worker();
        } else {
            workerToSave = workerRepository.findById(worker.getId())
                    .orElseThrow(() -> new EntityNotFoundException("worker with id " +
                            worker.getId() + " not found"));
        }
        workerToSave.setName(worker.getName());
        workerToSave.setDeleted(worker.getDeleted() == null ? workerToSave.isDeleted() : worker.getDeleted());
        return workerRepository.save(workerToSave);
    }

    @Override
    public Worker getWorker(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("worker with id " +
                        id + " not found"));
        worker.setDeleted(true);
        workerRepository.save(worker);
    }
}
