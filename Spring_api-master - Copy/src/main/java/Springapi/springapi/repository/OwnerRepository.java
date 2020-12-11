package Springapi.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Springapi.springapi.entity.OwnerModel;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, Long> {}
