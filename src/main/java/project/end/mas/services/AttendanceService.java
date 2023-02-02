package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.exceptions.CantJoinTournamentException;
import project.end.mas.models.Tournament;
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
     * <p> method showing all attendances in selected tournament</p>
     *
     * @param idTournament id of selected tournament
     * @return list of attendances in a chosen tournament
     */
    public List<Attendance> showAttendances(long idTournament) {
        return StreamSupport.stream(attendanceRepository.findAll().spliterator(), false).filter(p -> p.getTournament().getId() == idTournament).collect(Collectors.toList());
    }

    /**
     * <p> method adding new rider and motocross in a chosen tournament</p>
     *
     * @param idTournament id of selected tournament
     * @param idMotocross  id of selected motocross
     */
    public void joinTournament(Long idTournament, Long idMotocross) throws CantJoinTournamentException {
        //logged rider (hardcoded)
        Optional<Rider> loggedRider = riderRepository.findById(1L);
        Optional<Tournament> tournament = tournamentService.findTournamentById(idTournament);
        Optional<Motocross> motocross = motocrossRepository.findById(idMotocross);

        if (loggedRider.isPresent() && tournament.isPresent() && motocross.isPresent()) {
            Attendance attendance = new Attendance(loggedRider.get(), motocross.get(), tournament.get());
            attendanceRepository.save(attendance);
        } else {
            throw new CantJoinTournamentException("Can't join tournament, wrong data input!");
        }
    }
}