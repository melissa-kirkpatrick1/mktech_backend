package mk_tech.repository;

import mk_tech.models.Week;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepository extends CrudRepository<Week, Long> {
}
