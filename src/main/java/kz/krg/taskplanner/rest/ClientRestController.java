package kz.krg.taskplanner.rest;

import kz.krg.taskplanner.model.Client;
import kz.krg.taskplanner.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/client")
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @GetMapping("/list")
    public List<Client> getClients() {
        return clientService.listClients();
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }
}
