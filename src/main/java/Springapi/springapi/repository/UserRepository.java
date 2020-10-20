package Springapi.springapi.repository;

import Springapi.springapi.entity.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<UserModel, Integer> {

    UserModel findByLogin(String login);

}
