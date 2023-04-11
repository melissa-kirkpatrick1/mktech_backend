package mk_tech.repository;

import mk_tech.enums.StatusEnum;
import mk_tech.models.Timecard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimecardRepository extends CrudRepository<Timecard, Long> {
    List<Timecard> findTimecardByEmployeeId(Long employeeId);
    List<Timecard> findTimecardByEmployeeIdAndStatus(Long employeeId, StatusEnum status);
    @Query("from Timecard t " +
            "left outer join t.employee emp " +
            "left outer join t.week w " +
            "where emp.id = :employeeId and " +
            "w.id = :weekId")
    List<Timecard> findTimecardByEmployeeAndWeek(@Param("employeeId") Long employeeId, @Param("weekId") Long weekId);


    @Query("Select sum(th.numHours),ht.shortName, w.weekNumber, ht.billableHour, w.startWeekDate " +
            "from TimecardHour th " +
            "inner join th.timecard t " +
            "inner join t.week w " +
            "inner join t.employee e " +
            "inner join th.hourType ht " +
            "where e.id = :employeeId " +
            "group by ht.shortName, w.weekNumber, ht.billableHour, w.startWeekDate  " +
            "order by ht.shortName")
    List<Object> findHoursByReport(@Param("employeeId") Long employeeId);
}
