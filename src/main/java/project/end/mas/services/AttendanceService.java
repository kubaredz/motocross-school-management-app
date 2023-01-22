package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.exceptions.CantJoinTournamentException;
import project.end.mas.models.Competition;
import project.end.mas.models.Motocross;
import project.end.mas.models.Attendance;
import project.end.mas.models.Rider;
import project.end.mas.repositories.MotocrossRepository;
import project.end.mas.repositories.AttendanceRepository;
import project.end.mas.repositories.RiderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final RiderRepository riderRepository;
    private final MotocrossRepository motocrossRepository;
    private final TournamentService tournamentService;

    /**
     * <p> method showing all participations in selected competition</p>
     * @param idCompetition id of selected competition
     * @return list of participants in a chosen competition
     */
    public List<Attendance> showParticipants(long idCompetition) {
        return StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .filter(p -> p.getCompetition().getId() == idCompetition)
                .collect(Collectors.toList());
    }

    /**
     * <p> method adding new rider and horse in a chosen competition</p>
     * @param idCompetition id of selected competition
     * @param idHorse id of selected horse
     */
    public void joinCompetition(Long idCompetition, Long idHorse) throws CantJoinTournamentException {
        //logged rider (hardcoded)
        Optional<Rider> loggedRider = riderRepository.findById(1L);
        Optional<Competition> competition = tournamentService.findCompetitionById(idCompetition);
        Optional<Motocross> horse = motocrossRepository.findById(idHorse);

        if (loggedRider.isPresent() && competition.isPresent() && horse.isPresent()) {
            Attendance attendance = new Attendance(loggedRider.get(), horse.get(), competition.get());
            attendanceRepository.save(attendance);
        } else {
            throw new CantJoinTournamentException("Can't join competition, wrong data input!");
        }

    }

}
