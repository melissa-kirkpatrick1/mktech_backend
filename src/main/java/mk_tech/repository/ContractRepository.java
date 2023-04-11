package mk_tech.repository;

import mk_tech.models.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository  extends CrudRepository<Contract, Long> {
}
