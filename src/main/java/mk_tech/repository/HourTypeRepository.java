package mk_tech.repository;

import mk_tech.models.HourType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourTypeRepository  extends CrudRepository<HourType, Long> {
    List<HourType> findHourTypesByAllTimecards(Boolean allTimecards);

    @Query("select distinct(ht) from HourType ht left outer join ht.employees emp where emp.id = :employeeId" +
            " order by ht.allTimecards, ht.billableHour desc")
    List<HourType> findHourTypesByEmployee(@Param("employeeId") Long employeeId);
}

