package dashboard.energy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dashboard.energy.entities.Company;
import dashboard.energy.entities.User;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.payloads.entitiesDTO.CompanyDTO;
import dashboard.energy.repositories.CompanyDAO;
import dashboard.energy.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private Cloudinary cloudinary;

    public Company findById(UUID companyId){
        return companyDAO.findById(companyId).orElseThrow(()->new NotFoundException(companyId));
    }
    public Company saveCompany(User user, CompanyDTO body){
        Company newCompany = new Company();
        newCompany.setName(body.name());
        newCompany.setAddress(body.address());
        newCompany.setCompanyNumber(body.companyNumber());
        newCompany.setLogo("https://ui-avatars.com/api/?name=" + body.name() + "&length=9&font-size=0.15" + "&background=e4ba8e&color=fff");
        Company company = companyDAO.save(newCompany);
        userService.addCompanyToUser(user, newCompany.getCompanyId());
        return company;
    }
    public Optional<Company> getCompanyByUserId(User user){
        if(user.getCompany() == null){
            throw new NotFoundException("Utente " + user.getName() + " non ha un'azienda associata...");
        }
        Optional<Company> company = companyDAO.findById(user.getCompany().getCompanyId());
        if(company.isPresent()){
            return company;
        } else {
            throw new NotFoundException("Nessuna azienda con id  " + company.get().getCompanyId());
        }
    }
    public Company updateCompany(User user, CompanyDTO body){
        Company company = userService.findById(user.getUserId()).getCompany();
        if(body.name() != null) company.setName(body.name());
        if(body.companyNumber()!=null) company.setCompanyNumber(body.companyNumber());
        if(body.address() !=null) company.setAddress(body.address());
        return companyDAO.save(company);
    }
    public String uploadImage(MultipartFile file, User user) throws IOException {
        UUID companyId = user.getCompany().getCompanyId();
        Company company = companyDAO.findById(companyId).orElseThrow(()->new NotFoundException(companyId));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        company.setLogo(url);
        companyDAO.save(company);
        return url;
    }
}
