package uz.freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.freelancer.entity.Users;
import uz.freelancer.peyload.ApiResponse;
import uz.freelancer.peyload.ReqUsers;
import uz.freelancer.repository.UsersRepository;
import uz.freelancer.service.UsersService;

import java.util.List;

@Controller
@RequestMapping("api/freelancer")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersService usersService;

    @GetMapping
    public HttpEntity<?> getAllUsers(){
        List<Users> all = usersRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editUsers(@PathVariable Integer id, @RequestBody ReqUsers reqUsers){
        ApiResponse apiResponse = usersService.editUsers(reqUsers, id);
        return ResponseEntity.ok(apiResponse);
    }
}
