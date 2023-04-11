package mk_tech.service;

import mk_tech.enums.StatusEnum;
import mk_tech.models.*;
import mk_tech.repository.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class TimecardServiceImpl implements TimecardService {
    private final TimecardRepository timecardRepository;
    private final WeekRepository weekRepository;
    private final HourTypeRepository hourTypeRepository;
    private final HourRepository hourRepository;
    private final EmployeeRepository employeeRepository;

    public TimecardServiceImpl(TimecardRepository timecardRepository,
                               WeekRepository weekRepository,
                               HourRepository hourRepository,
                               HourTypeRepository hourTypeRepository,
                               EmployeeRepository employeeRepository) {
        this.timecardRepository = timecardRepository;
        this.weekRepository = weekRepository;
        this.hourTypeRepository = hourTypeRepository;
        this.hourRepository = hourRepository;
        this.employeeRepository = employeeRepository;
    }
    public Timecard findTimecardById(Long timecardId) {
        return this.timecardRepository.findById(timecardId).get();
    }

    public List<Timecard> findTimecardsByUser(Long employeeId) {
        return this.timecardRepository.findTimecardByEmployeeId(employeeId);
    }

    public List<Week> getTimecardWeeks() {
        return (List<Week>) this.weekRepository.findAll();
    }
    public List<HourType> getHourTypesByEmployee(Long employeeId) {
        return this.hourTypeRepository.findHourTypesByEmployee(employeeId);

    }
    public List<Timecard> findTimecardsByUserIdAndStatus(Long employeeId, StatusEnum status) {
        return this.timecardRepository.findTimecardByEmployeeIdAndStatus(employeeId, status);
    }

    public List<Timecard> findTimecardsByUserIdAndWeek(Long employeeId, Long weekId) {
        return this.timecardRepository.findTimecardByEmployeeAndWeek(employeeId, weekId);
    }
    public List<Timecard> findAllTimecards() {
        return (List<Timecard>) this.timecardRepository.findAll();
    }
    public void saveTimecard(Timecard timecard) {
        if (timecard.getHours() != null) {
           for(TimecardHour hour: timecard.getHours()) {
               hour.setTimecard(timecard);
           }
        }
        this.timecardRepository.save(timecard);
    }
    public Double getCurrentTargetHours(Long employeeId) throws ParseException {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        LocalDate now = LocalDate.now(); // 2015-11-23

        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<TimecardHour> timecardHours = this.hourRepository.findHoursByEmployee(employeeId);
        if (!timecardHours.isEmpty()) {
            Date date = timecardHours.get(0).getHourDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = format.parse(year+"-01-01");

        if (!timecardHours.isEmpty()) {
            Date lastHourDate = timecardHours.get(0).getHourDate();
            Instant firstDateTime = firstDate.toInstant();
            Instant lastDateTime = lastHourDate.toInstant();
            Long daysBetweenDates = ChronoUnit.DAYS.between(firstDateTime,lastDateTime);
            Double targetHours = (Double) ((double) 1860 )/ 365;
            targetHours = targetHours * daysBetweenDates;
            return targetHours;
        }
        return null;
    }
    public List<HourSummary> findHoursSummaryByEmployee(Long employeeId) {
        List<Object> results = this.timecardRepository.findHoursByReport(employeeId);

        Map<JSONObject, List<HourTotal>> hourMap = new HashMap<>();
        for (Object result: results) {
            Object[] row = (Object[]) result;
            HourTotal hourTotal = new HourTotal();
            hourTotal.setTotalHours((Double) row[0]);
            hourTotal.setHourTypeName((String) row[1]);
            Integer weekNumber = (Integer) row[2];
            hourTotal.setBillableHour((Boolean) row[3]);
            Date weekStartDate = (Date) row[4];

            JSONObject key = new JSONObject();
            key.put("weekNumber", weekNumber);
            key.put("startDate", weekStartDate);
            if (hourMap.get(key) == null) {
                hourMap.put(key, new ArrayList<HourTotal>());
            }
            hourMap.get(key).add(hourTotal);
        }
        List<HourSummary> hourSummaries = new ArrayList<>();
        for (JSONObject key : hourMap.keySet())
        {
            HourSummary hourSummary = new HourSummary();
            hourSummary.setWeekNumber(((Integer)key.get("weekNumber")).longValue());
            hourSummary.setWeekStartDate((Date) key.get("weekStartDate"));
            hourSummary.setHourTotals(hourMap.get(key));
            hourSummaries.add(hourSummary);
        }
        return hourSummaries;
    }
}
