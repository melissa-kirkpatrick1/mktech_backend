package mk_tech.service;

import mk_tech.enums.StatusEnum;
import mk_tech.models.*;

import java.sql.Time;
import java.text.ParseException;
import java.util.List;

public interface TimecardService {
    Timecard findTimecardById(Long timecardId);
    List<Timecard> findTimecardsByUser(Long employeeId);
    List<Timecard> findAllTimecards();
    void saveTimecard(Timecard timecard);
    List<Week> getTimecardWeeks();
    List<HourType> getHourTypesByEmployee(Long employeeId);
    List<Timecard> findTimecardsByUserIdAndStatus(Long employeeId, StatusEnum status);
    List<Timecard> findTimecardsByUserIdAndWeek(Long employeeId, Long weekId);
    List<HourSummary> findHoursSummaryByEmployee(Long employeeId);
    Double getCurrentTargetHours(Long employeeId) throws ParseException;
}
