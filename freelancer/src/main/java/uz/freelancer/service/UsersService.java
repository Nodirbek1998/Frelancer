package uz.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.freelancer.entity.Users;
import uz.freelancer.peyload.ApiResponse;
import uz.freelancer.peyload.ReqUsers;
import uz.freelancer.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addUsers(ReqUsers reqUsers){
        boolean exists = usersRepository.existsByUsername(reqUsers.getUsername());

        if (!exists){
            Users users = new Users();
            users.setName(reqUsers.getName());
            users.setUsername(reqUsers.getUsername());
            users.setPersonType(reqUsers.getPersonType());
            users.setPhone(reqUsers.getPhone());
            users.setPassword(passwordEncoder.encode(reqUsers.getPassword()));
            usersRepository.save(users);
            return new ApiResponse("Siz registiratsiyadan muvaffaqiyatli o'tingiz", true);
        }
        return new ApiResponse("Bunday username oldin ishlatilgan", false);
    }

    public ApiResponse editUsers(ReqUsers reqUsers, Integer id){
        boolean exists = usersRepository.existsByUsernameAndIdNot(reqUsers.getUsername(), id);
        if (!exists){
            Users users = usersRepository.findById(id).get();
            users.setName(reqUsers.getName());
            users.setUsername(reqUsers.getUsername());
            users.setPersonType(reqUsers.getPersonType());
            users.setPhone(reqUsers.getPhone());
            users.setPassword(passwordEncoder.encode(reqUsers.getPassword()));
            usersRepository.save(users);
            return new ApiResponse("Malumot o'zgartirildi", true);
        }
        return new ApiResponse("Malumot o'zgartirilmadi", false);
    }


    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(username));
    }
}
