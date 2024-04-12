package kz.krg.taskplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.krg.taskplanner.model.Status;
import kz.krg.taskplanner.repository.StatusRepository;
import kz.krg.taskplanner.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Status getStatus(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public List<Status> listStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status save(Status status) {
        Status statusToSave;
        if (status.getId() == null) {
            statusToSave = new Status();
        } else {
            statusToSave = statusRepository.findById(status.getId())
                    .orElseThrow(() -> new EntityNotFoundException("status with id " +
                            status.getId() + " not found"));
        }
        statusToSave.setStatus(status.getStatus());

        return statusRepository.save(statusToSave);
    }
}
