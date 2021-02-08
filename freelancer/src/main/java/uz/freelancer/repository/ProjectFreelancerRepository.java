package uz.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.freelancer.entity.ProjectFreelancer;

import java.util.List;

public interface ProjectFreelancerRepository extends JpaRepository<ProjectFreelancer, Integer> {
    List<ProjectFreelancer> findByProjectId(Integer projectId);
}
