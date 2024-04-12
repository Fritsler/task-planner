package kz.krg.taskplanner.service.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.krg.taskplanner.model.Client;
import kz.krg.taskplanner.repository.ClientRepository;
import kz.krg.taskplanner.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        Client clientToSave;
        if (client.getId() == null) {
            clientToSave = clientRepository.findFirstByPhoneNumber(client.getPhoneNumber())
                    .orElse(new Client());
        } else {
            clientToSave = clientRepository.findById(client.getId())
                    .orElseThrow(() -> new EntityNotFoundException("client with id " +
                            client.getId() + " not found"));
        }
        clientToSave.setFio(client.getFio());
        clientToSave.setPhoneNumber(client.getPhoneNumber());
        clientToSave.setEmail(client.getEmail());

        return clientRepository.save(clientToSave);
    }
}
