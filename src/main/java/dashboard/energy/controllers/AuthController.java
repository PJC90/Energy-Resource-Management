package dashboard.energy.controllers;

import dashboard.energy.entities.User;
import dashboard.energy.exceptions.BadRequestException;
import dashboard.energy.payloads.entitiesDTO.UserDTO;
import dashboard.energy.payloads.entitiesDTO.UserResponseDTO;
import dashboard.energy.payloads.login.LoginDTO;
import dashboard.energy.payloads.login.LoginResponseDTO;
import dashboard.energy.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            User newUser = authService.save(newUserPayload);
            return new UserResponseDTO(newUser.getUserId());
        }
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body){
        String accessToken = authService.authenticateUser(body);
        return new LoginResponseDTO(accessToken);
    }
}
