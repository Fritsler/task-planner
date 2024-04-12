package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Status;

import java.util.List;

public interface StatusService {
    Status getStatus(Long id);
    List<Status> listStatuses();
    Status save(Status status);
}
