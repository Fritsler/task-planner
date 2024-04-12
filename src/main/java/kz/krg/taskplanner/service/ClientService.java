package kz.krg.taskplanner.service;

import kz.krg.taskplanner.model.Client;

import java.util.List;

public interface ClientService {
    Client getClient(Long id);
    List<Client> listClients();
    Client save(Client client);
}
