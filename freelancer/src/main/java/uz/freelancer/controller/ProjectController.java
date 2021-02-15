package uz.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.freelancer.entity.Project;
import uz.freelancer.peyload.ApiResponse;
import uz.freelancer.peyload.ReqProject;
import uz.freelancer.repository.ProjectFreelancerRepository;
import uz.freelancer.repository.ProjectRepository;
import uz.freelancer.repository.UsersRepository;
import uz.freelancer.service.ProjectService;

import java.util.List;

@Controller
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectFreelancerRepository projectAndFreelancerRepository;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public HttpEntity<?> getAllProject(){
        List<Project> all = projectRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @PostMapping
    public HttpEntity<?> addProject(@RequestBody ReqProject reqProject){
        ApiResponse apiResponse = projectService.addProject(reqProject);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProject(@PathVariable Integer id, @RequestBody ReqProject reqProject){
        ApiResponse apiResponse = projectService.editProject(reqProject, id);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public  HttpEntity<?> deleteProject(@PathVariable Integer id){
         projectRepository.deleteById(id);
         return ResponseEntity.ok(new ApiResponse("Project o'chirildi", true));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getFreelancers(@PathVariable Integer id){


        return ResponseEntity.ok("allByIdLike");
    }
}
