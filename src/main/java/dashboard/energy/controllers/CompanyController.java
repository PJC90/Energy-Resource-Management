package dashboard.energy.controllers;

import dashboard.energy.entities.Company;
import dashboard.energy.entities.User;
import dashboard.energy.exceptions.BadRequestException;
import dashboard.energy.payloads.entitiesDTO.CompanyDTO;
import dashboard.energy.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @GetMapping
    public Optional<Company> getMyCompany(@AuthenticationPrincipal User user){
        return companyService.getCompanyByUserId(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createNewCompany(@AuthenticationPrincipal User user, @RequestBody @Validated CompanyDTO payload, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return companyService.saveCompany(user, payload);
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company updateCompany(@AuthenticationPrincipal User user, @RequestBody  CompanyDTO payload){
            return companyService.updateCompany(user,payload);
    }
    @PatchMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadLogo(@RequestParam("logo")MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        return companyService.uploadImage(file, user);
    }
}
