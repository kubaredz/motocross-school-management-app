package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Competition;

@Repository
public interface TournamentRepository extends CrudRepository<Competition, Long> {
}
