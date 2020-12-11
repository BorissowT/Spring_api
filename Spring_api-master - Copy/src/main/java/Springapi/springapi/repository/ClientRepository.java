package Springapi.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Springapi.springapi.entity.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {}