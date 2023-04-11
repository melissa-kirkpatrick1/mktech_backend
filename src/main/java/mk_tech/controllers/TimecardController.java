package mk_tech.controllers;

import mk_tech.enums.StatusEnum;
import mk_tech.models.*;
import mk_tech.service.TimecardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("timecard-api")
public class TimecardController {
    private final TimecardService timecardService;

    public TimecardController(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    @GetMapping(path = "/timecard/{timecardId}")
    public Timecard getTimecard(@PathVariable Long timecardId, Authentication authentication) {
        return this.timecardService.findTimecardById(timecardId);
    }

    @PostMapping(path = "/timecard/save")
    public Timecard saveTimecard(@RequestBody Timecard timecard, Authentication authentication) {
        this.timecardService.saveTimecard(timecard);
        return timecard;
    }

    @GetMapping(path = "/timecards/{employeeId}")
    public List<Timecard> getTimecardsByEmployee(@PathVariable Long employeeId, Authentication authentication) {
        return this.timecardService.findTimecardsByUser(employeeId);
    }

    @GetMapping(path = "/active-timecard")
    public Timecard getActiveTimecard(Authentication authentication, @RequestParam("employeeId") Long employeeId) {
        SecurityContext sc = SecurityContextHolder.getContext();
        List<Timecard> timecards = timecardService.findTimecardsByUserIdAndStatus(employeeId, StatusEnum.ACTIVE);
        return timecards.size() > 0 ? timecards.get(0) : null;
    }

    @GetMapping(path = "/by-week")
    public Timecard getTimecardByWeekId(@RequestParam("weekId") Long weekId, @RequestParam("employeeId") Long employeeId, Authentication authentication) {
        SecurityContext sc = SecurityContextHolder.getContext();
        List<Timecard> timecards = this.timecardService.findTimecardsByUserIdAndWeek(employeeId, weekId);
        return timecards.size() > 0 ? timecards.get(0) : null;
    }

    @GetMapping(path = "/weeks")
    public List<Week> getTimecardWeeks(Authentication authentication) {
        return this.timecardService.getTimecardWeeks();
    }
    @GetMapping(path = "/statuses")
    public List<StatusEnum> getStatuses(Authentication authentication) {
        List<String> statuses = new ArrayList<>();
        return Arrays.stream(StatusEnum.values()).collect(Collectors.toList());
    }
    @GetMapping(path = "/hour-types")
    public List<HourType> getHourTypesByEmployee( @RequestParam("employeeId") Long employeeId) {
        SecurityContext sc = SecurityContextHolder.getContext();
        return this.timecardService.getHourTypesByEmployee(employeeId);
    }
    @GetMapping(path = "/hours-report")
    public List<HourSummary> getHoursReport(@RequestParam("employeeId") Long employeeId, Authentication authentication) {
        return this.timecardService.findHoursSummaryByEmployee(employeeId);
    }

    @GetMapping(path = "/current-target-hours")
    public Map<String, Double> getCurrentTargetHours(@RequestParam("employeeId") Long employeeId, Authentication authentication) throws Exception {
        Map<String, Double> targetMap = new HashMap<>();
        targetMap.put("targetHours", this.timecardService.getCurrentTargetHours(employeeId));
        return targetMap;
    }
}
