package dashboard.energy.services;

import dashboard.energy.entities.Company;
import dashboard.energy.entities.User;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.payloads.entitiesDTO.CompanyResponseDTO;
import dashboard.energy.repositories.CompanyDAO;
import dashboard.energy.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CompanyDAO companyDAO;

    public User findById(UUID userId){
        return userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));
    }
    public User findByEmail(String email){
        return userDAO.findByEmail(email).orElseThrow(()->new NotFoundException("Utente con email: " + email + " non trovato"));
    }

    public UUID addCompanyToUser(User user, CompanyResponseDTO body){
        Company found = companyDAO.findById(body.companyId()).orElseThrow(()->new NotFoundException(body.companyId()));
        user.setCompany(found);
        userDAO.save(user);
        return user.getCompany().getCompanyId();
    }
}
