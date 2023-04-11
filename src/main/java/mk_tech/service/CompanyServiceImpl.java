package mk_tech.service;

import mk_tech.models.ContactForm;
import mk_tech.models.Contract;
import mk_tech.models.HourType;
import mk_tech.models.Week;
import mk_tech.repository.ContactFormRepository;
import mk_tech.repository.ContractRepository;
import mk_tech.repository.HourTypeRepository;
import mk_tech.repository.WeekRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final ContractRepository contractRepository;
    private final HourTypeRepository hourTypeRepository;
    private final WeekRepository weekRepository;
    private final ContactFormRepository contactFormRepository;
    CompanyServiceImpl(ContractRepository contractRepository,
                       HourTypeRepository hourTypeRepository,
                       WeekRepository weekRepository,
                       ContactFormRepository contactFormRepository) {
        this.contractRepository = contractRepository;
        this.hourTypeRepository = hourTypeRepository;
        this.weekRepository = weekRepository;
        this.contactFormRepository = contactFormRepository;
    }
    @Override
    public void saveContract(Contract contract) {
        this.contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return ( List<Contract>) this.contractRepository.findAll();
    }

    @Override
    public void saveHourType(HourType hourType) {
        this.hourTypeRepository.save(hourType);
    }

    @Override
    public List<HourType> findAllHourTypes() {
        return (List<HourType>) this.hourTypeRepository.findAll();
    }

    @Override
    public List<HourType> findHourTypesByAllTimecards(Boolean allTimecards) {
        return this.hourTypeRepository.findHourTypesByAllTimecards(allTimecards);
    }

    public void saveContactForm(ContactForm contactForm) {
        this.contactFormRepository.save(contactForm);
    }
    public List<ContactForm> getAllContactForms() {
        return  (List<ContactForm>) this.contactFormRepository.findAll();
    }

}
