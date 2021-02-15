package uz.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.freelancer.entity.Project;
import uz.freelancer.entity.Users;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    boolean existsByProjectNameAndIdNot(String projectName, Integer id);

    List<Project> findAllByUsers(Users users);
    List<Project> findAllByUsersId(Integer id);
}
