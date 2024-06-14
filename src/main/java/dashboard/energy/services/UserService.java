package dashboard.energy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dashboard.energy.entities.Company;
import dashboard.energy.entities.User;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.payloads.entitiesDTO.CompanyResponseDTO;
import dashboard.energy.payloads.entitiesDTO.UserDTO;
import dashboard.energy.repositories.CompanyDAO;
import dashboard.energy.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private Cloudinary cloudinary;

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
    public User updateUser(UUID userId, UserDTO body){
        User update = userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));
        if(body.name() != null){update.setName(body.name());}
        if(body.surname() != null){update.setSurname(body.surname());}
        if(body.username() != null){update.setUsername(body.username());}
        if(body.email() != null){update.setEmail(body.email());}
        if(body.birthday() != null){update.setBirthday(body.birthday());}
        return userDAO.save(update);
    }

    public String uploadImage(MultipartFile file, UUID userId) throws IOException {
        User found = userDAO.findById(userId).orElseThrow(()->new NotFoundException(userId));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        userDAO.save(found);
        return url;
    }
}
