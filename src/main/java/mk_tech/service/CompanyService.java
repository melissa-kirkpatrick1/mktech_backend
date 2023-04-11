package mk_tech.service;

import mk_tech.models.ContactForm;
import mk_tech.models.Contract;
import mk_tech.models.HourType;

import java.util.List;

public interface CompanyService {
    void saveContract(Contract contract);
    List<Contract> getAllContracts();
    void saveHourType(HourType hourType);
    List<HourType> findAllHourTypes();
    List<HourType> findHourTypesByAllTimecards(Boolean allTimecards);

    void saveContactForm(ContactForm contactForm);
    List<ContactForm> getAllContactForms();
}
