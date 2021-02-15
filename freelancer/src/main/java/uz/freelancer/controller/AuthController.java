package uz.freelancer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.freelancer.entity.Users;
import uz.freelancer.entity.enums.PersonType;
import uz.freelancer.peyload.ApiResponse;
import uz.freelancer.peyload.ReqLogin;
import uz.freelancer.peyload.ReqUsers;
import uz.freelancer.repository.ProjectRepository;
import uz.freelancer.repository.UsersRepository;
import uz.freelancer.security.JwtProvider;
import uz.freelancer.service.UsersService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    UsersService usersService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody ReqUsers reqUsers){
        ApiResponse apiResponse = usersService.addUsers(reqUsers);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> signIn(@RequestBody ReqLogin reqLogin){
       try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                   reqLogin.getUsername(),
                   reqLogin.getPassword()));
            String token = jwtProvider.generateToken((Users)authentication.getPrincipal());
            Users byUsername = usersRepository.findByUsername(reqLogin.getUsername()).get();
           Map<String, Object> json = new HashMap<>();
           json.put("name",byUsername.getName());
           json.put("username",byUsername.getUsername());
           json.put("phone",byUsername.getPhone());
           json.put("id",byUsername.getId());
           json.put("personType",byUsername.getPersonType());
           json.put("token",token);
           if (PersonType.customer == byUsername.getPersonType()){
               json.put("projects", projectRepository.findAllByUsers(usersRepository.findById(byUsername.getId()).get()));
           }else{
               json.put("projects",projectRepository.findAll());
           }
           return ResponseEntity.ok(json);
       }catch (Exception e){
            e.printStackTrace();
           return ResponseEntity.status(409).body(new ApiResponse("Parol yoki login xato", false));
       }
    }
}
