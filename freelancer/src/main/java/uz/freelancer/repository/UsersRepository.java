package uz.freelancer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.freelancer.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Integer id);

}
