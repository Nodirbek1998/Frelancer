package uz.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.freelancer.entity.Project;
import uz.freelancer.entity.ProjectFreelancer;
import uz.freelancer.peyload.ReqTakeProject;
import uz.freelancer.repository.ProjectFreelancerRepository;
import uz.freelancer.repository.ProjectRepository;
import uz.freelancer.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/take")
public class ProjectFreelancerController {
    @Autowired
    ProjectFreelancerRepository projectFreelancerRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UsersRepository usersRepository;
    @PostMapping
    public HttpEntity<?> takeProject(@RequestBody ReqTakeProject reqTakeProject){
        ProjectFreelancer projectAndFreelancer = new ProjectFreelancer();
        projectAndFreelancer.setProjectId(reqTakeProject.getProjectId());
        projectAndFreelancer.setUsersId(reqTakeProject.getProjectId());
        projectFreelancerRepository.save(projectAndFreelancer);
        return ResponseEntity.ok("Javobingiz Costomer ga jo'natildi");
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getAll(@PathVariable Integer id){
        List<ProjectFreelancer> allByProjectId = projectFreelancerRepository.findByProjectId(id);
        List<Integer> usersId = new ArrayList<>();
        allByProjectId.forEach(users->usersId.add(users.getUsersId()));
        List<Project> allById = projectRepository.findAllById(usersId);
        return ResponseEntity.ok(allByProjectId);

    }
}
