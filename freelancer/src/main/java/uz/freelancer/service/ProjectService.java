package uz.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.freelancer.entity.Project;
import uz.freelancer.entity.Users;
import uz.freelancer.peyload.ApiResponse;
import uz.freelancer.peyload.ReqProject;
import uz.freelancer.repository.ProjectRepository;
import uz.freelancer.repository.UsersRepository;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UsersRepository usersRepository;

    public ApiResponse addProject(ReqProject reqProject){
        Project project = new Project();
        save(reqProject, project);
        return new ApiResponse("Project saqlandi", true);
    }

    public ApiResponse editProject(ReqProject reqProject, Integer id){
        boolean exists = projectRepository.existsByProjectNameAndIdNot(reqProject.getProjectName(), id);
        if (!exists){
            Project byId = projectRepository.findById(id).get();
            save(reqProject, byId);
            return new ApiResponse("Project saqlandi", true);
        }
        return new ApiResponse("Project o'zgartirildi", false);
    }
     void save(ReqProject reqProject, Project project){
         Users users = usersRepository.findById(reqProject.getUsers()).get();
         project.setProjectName(reqProject.getProjectName());
         project.setPrice(reqProject.getPrice());
         project.setDescription(reqProject.getDescription());
         project.setCategory(reqProject.getCategory());
         project.setStatus(reqProject.getStatus());
         project.setUsers(users);
         projectRepository.save(project);
     }
}
