package Springapi.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Springapi.springapi.entity.StoreModel;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, Long> {}
