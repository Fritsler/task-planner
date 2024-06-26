package kz.krg.taskplanner.repository;

import kz.krg.taskplanner.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findFirstByPhoneNumber(String phoneNumber);
}
