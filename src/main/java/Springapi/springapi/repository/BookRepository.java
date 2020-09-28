package Springapi.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Springapi.springapi.entity.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {}