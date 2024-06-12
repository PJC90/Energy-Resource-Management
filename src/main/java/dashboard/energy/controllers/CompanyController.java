package dashboard.energy.controllers;

import dashboard.energy.entities.Company;
import dashboard.energy.exceptions.BadRequestException;
import dashboard.energy.payloads.entitiesDTO.CompanyDTO;
import dashboard.energy.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Company createNewCompany(@RequestBody @Validated CompanyDTO payload, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return companyService.saveCompany(payload);
        }
    }
}
