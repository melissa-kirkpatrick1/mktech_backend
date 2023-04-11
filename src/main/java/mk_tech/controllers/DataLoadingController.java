package mk_tech.controllers;

import mk_tech.models.Timecard;
import mk_tech.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("data-api")
public class DataLoadingController {
    private final DataService dataService;
    public DataLoadingController(DataService dataService) {
        this.dataService = dataService;
    }
    @GetMapping(path = "/loadWeeks")
    public HttpStatus loadWeeks(Authentication authentication) {
        this.dataService.loadWeeks();
        return HttpStatus.OK;
    }
}
