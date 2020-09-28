package Springapi.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Springapi.springapi.entity.AuthorModel;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {}
