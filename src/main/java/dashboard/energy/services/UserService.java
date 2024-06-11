package dashboard.energy.services;

import dashboard.energy.entities.User;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User findById(UUID userId){
        return userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));
    }
    public User findByEmail(String email){
        return userDAO.findByEmail(email).orElseThrow(()->new NotFoundException("Utente con email: " + email + " non trovato"));
    }
}
