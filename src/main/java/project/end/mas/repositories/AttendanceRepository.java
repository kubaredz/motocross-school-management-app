package project.end.mas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.end.mas.models.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long>{
}
