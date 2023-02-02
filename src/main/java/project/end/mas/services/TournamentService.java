package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.enums.TournamentState;
import project.end.mas.models.Tournament;
import project.end.mas.repositories.TournamentRepository;
import project.end.mas.repositories.AttendanceRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final AttendanceRepository attendanceRepository;

    /**
     * <p> method showing all open tournaments at the moment/p>
     *
     * @return list of open tournaments
     */
    public Iterable<Tournament> showOpenTournaments() {
        StreamSupport.stream(tournamentRepository.findAll().spliterator(), false)
                .forEach(this::setAttendancesNumber);

        return StreamSupport
                .stream(tournamentRepository.findAll().spliterator(), false)
                .filter(tournament -> tournament.getTournamentState().equals(TournamentState.OPEN))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checks if there is at least one open tournament at the moment </p>
     *
     * @return true if there is any open tournament
     */
    public boolean checkOpen() {
        return StreamSupport
                .stream(showOpenTournaments().spliterator(), false)
                .count() > 0;
    }

    /**
     * <p> method that try to find a tournament by a given id </p>
     *
     * @param idTournament id of a tournament to find
     * @return tournament or empty optional
     */
    public Optional<Tournament> findTournamentById(long idTournament) {
        return tournamentRepository.findById(idTournament);
    }

    /**
     * <p> method that cancels tournament (changes its state to cancelled) </p>
     *
     * @param idTournament id of a tournament to cancel
     */
    public void cancel(long idTournament) {
        tournamentRepository
                .findById(idTournament)
                .ifPresent(tournament -> {
                    tournament.setTournamentState(TournamentState.CANCELLED);
                    tournamentRepository.save(tournament);
                });
    }

    /**
     * <p> method that opens tournament for a registration process (changes its state to open) </p>
     *
     * @param idTournament id of a tournament to cancel
     */
    public void open(long idTournament) {
        tournamentRepository
                .findById(idTournament)
                .ifPresent(tournament -> {
                    tournament.setTournamentState(TournamentState.OPEN);
                    tournamentRepository.save(tournament);
                });
    }

    public void setAttendancesNumber(Tournament tournament) {
        int number = StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .filter(p -> p.getTournament().getId() == tournament.getId())
                .collect(Collectors.toList())
                .size();
        tournament.setAttendancesNumber(number);
    }
}