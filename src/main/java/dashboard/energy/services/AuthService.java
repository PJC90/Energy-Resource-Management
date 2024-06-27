package dashboard.energy.services;

import dashboard.energy.entities.User;
import dashboard.energy.entities.enums.Roles;
import dashboard.energy.exceptions.BadRequestException;
import dashboard.energy.exceptions.UnauthorizedException;
import dashboard.energy.payloads.entitiesDTO.UserDTO;
import dashboard.energy.payloads.login.LoginDTO;
import dashboard.energy.repositories.UserDAO;
import dashboard.energy.security.JWTtools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JWTtools jwTtools;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUser(LoginDTO body){
        User foundUser = userService.findByEmail(body.email());
        if(passwordEncoder.matches(body.password(), foundUser.getPassword())){
            return jwTtools.createToken(foundUser);
        }else{
            throw new UnauthorizedException("Credenziali non valide...");
        }
    }

    public User save(UserDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {throw new BadRequestException("email: " + user.getEmail() + " già in uso...");});
        userDAO.findByUsername(body.username()).ifPresent(user -> {throw new BadRequestException("username:  " + user.getUsername() + " già in uso...");});
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setBirthday(body.birthday());
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname() + "&background=0DCAF0&color=fff");
        newUser.setRole(Roles.USER);
        return userDAO.save(newUser);
    }
}
