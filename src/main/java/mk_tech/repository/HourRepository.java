package mk_tech.repository;

import mk_tech.models.HourType;
import mk_tech.models.TimecardHour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourRepository  extends CrudRepository<TimecardHour, Long> {

    @Query("from TimecardHour th " +
            "left outer join th.timecard t " +
            "left outer join t.employee emp " +
            "where emp.id = :employeeId order by " +
            "th.hourDate desc")
    List<TimecardHour> findHoursByEmployee(@Param("employeeId") Long employeeId);
}

