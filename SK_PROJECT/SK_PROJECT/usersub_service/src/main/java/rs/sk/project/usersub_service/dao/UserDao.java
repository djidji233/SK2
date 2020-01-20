package rs.sk.project.usersub_service.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.sk.project.usersub_service.domain.User;

import java.util.Optional;

public interface UserDao extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
