package dashboard.energy.services;

import dashboard.energy.entities.Company;
import dashboard.energy.exceptions.NotFoundException;
import dashboard.energy.payloads.entitiesDTO.CompanyDTO;
import dashboard.energy.repositories.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService {
    @Autowired
    private CompanyDAO companyDAO;

    public Company findById(UUID companyId){
        return companyDAO.findById(companyId).orElseThrow(()->new NotFoundException(companyId));
    }
    public Company saveCompany(CompanyDTO body){
        Company newCompany = new Company();
        newCompany.setName(body.name());
        newCompany.setAddress(body.address());
        newCompany.setCompanyNumber(body.companyNumber());
        newCompany.setLogo("https://ui-avatars.com/api/?name=" + body.name() + "&length=9&font-size=0.15" + "&background=e4ba8e&color=fff");
        return companyDAO.save(newCompany);
    }
}
