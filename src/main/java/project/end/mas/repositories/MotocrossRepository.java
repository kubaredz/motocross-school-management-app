package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Motocross;

@Repository
public interface MotocrossRepository extends CrudRepository<Motocross, Long> {
}
