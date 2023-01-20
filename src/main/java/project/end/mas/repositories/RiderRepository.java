package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Rider;

@Repository
public interface RiderRepository extends CrudRepository<Rider, Long>{
}
