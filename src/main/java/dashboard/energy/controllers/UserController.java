package dashboard.energy.controllers;

import dashboard.energy.entities.User;
import dashboard.energy.payloads.entitiesDTO.CompanyResponseDTO;
import dashboard.energy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
}
