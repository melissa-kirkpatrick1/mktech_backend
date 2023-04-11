package mk_tech.controllers;

import mk_tech.models.ContactForm;
import mk_tech.models.Contract;
import mk_tech.models.Employee;
import mk_tech.models.HourType;
import mk_tech.service.CompanyService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("company-api")
public class CompanyController {
    private CompanyService companyService;
    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(path="/contract", consumes = "application/json", produces = "application/json")
    public void saveContract(@RequestBody Contract contract, Authentication authentication) {
        this.companyService.saveContract(contract);
    }

    @GetMapping("/contracts")
    public List<Contract> getContracts() {
        return this.companyService.getAllContracts();
    }

    @PostMapping(path="/hourType", consumes = "application/json", produces = "application/json")
    public void saveHourType(@RequestBody HourType hourType, Authentication authentication) {
        this.companyService.saveHourType(hourType);
    }

    @GetMapping("/hourTypes")
    public List<HourType> getHourTypes() {
        return this.companyService.findAllHourTypes();
    }

    @PostMapping(path="/contact", consumes = "application/json", produces = "application/json")
    public ContactForm saveContactForm(@RequestBody ContactForm contactForm, Authentication authentication) {
        this.companyService.saveContactForm(contactForm);
        return contactForm;
    }

    @GetMapping("/contact-forms")
    public List<ContactForm> getContactForms() {
        return this.companyService.getAllContactForms();
    }

}
