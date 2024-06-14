package dashboard.energy.controllers;

import dashboard.energy.entities.User;
import dashboard.energy.payloads.entitiesDTO.CompanyResponseDTO;
import dashboard.energy.payloads.entitiesDTO.UserDTO;
import dashboard.energy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addCompanyToUser(@AuthenticationPrincipal User user, @RequestBody CompanyResponseDTO companyId){
        return userService.addCompanyToUser(user,companyId);
    }
    @GetMapping("/me")
    public User myProfile(@AuthenticationPrincipal User user){
        return userService.findById(user.getUserId());
    }
    @PutMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@AuthenticationPrincipal User user, @RequestBody UserDTO body){
        return userService.updateUser(user.getUserId(), body);
    }
    @PatchMapping("/me/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadImage(@RequestParam("image")MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        return userService.uploadImage(file, user.getUserId());
    }
}
