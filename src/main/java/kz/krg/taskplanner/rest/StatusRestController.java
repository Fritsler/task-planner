package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Status;
import kz.krg.taskplanner.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/status")
@RequiredArgsConstructor
public class StatusRestController {
    private final StatusService statusService;

    @GetMapping("/list")
    public List<Status> getStatuses() {
        return statusService.listStatuses();
    }

    @PostMapping
    public Status saveStatus(@RequestBody Status status) {
        return statusService.save(status);
    }
}
