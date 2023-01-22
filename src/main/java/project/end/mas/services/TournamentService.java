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
     * <p> method showing all open competitions at the moment/p>
     * @return list of open competitions
     */
    public Iterable<Tournament> showOpenCompetitions() {
        StreamSupport.stream(tournamentRepository.findAll().spliterator(), false)
                .forEach(this::setParticipantsNumber);

        return StreamSupport
                .stream(tournamentRepository.findAll().spliterator(), false)
                .filter(competition -> competition.getTournamentState().equals(TournamentState.OPEN))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checkes if there is at least one open competiton at the moment </p>
     * @return true if there is any open competition
     */
    public boolean checkOpen() {
        return StreamSupport
                .stream(showOpenCompetitions().spliterator(), false)
                .count() > 0;
    }

    /**
     * <p> method that try to find a competition by a given id </p>
     * @param idCompetition id of a competition to find
     * @return competition or empty optional
     */
    public Optional<Tournament> findCompetitionById(long idCompetition) {
        return tournamentRepository.findById(idCompetition);
    }

    /**
     * <p> method that cancels competition (changes its state to cancelled) </p>
     * @param idCompetition id of a competition to cancel
     */
    public void cancel(long idCompetition) {
        tournamentRepository
                .findById(idCompetition)
                .ifPresent(competition -> {
                    competition.setTournamentState(TournamentState.CANCELLED);
                    tournamentRepository.save(competition);
                });
    }

    /**
     * <p> method that opens competition for a registration process (changes its state to open) </p>
     * @param idCompetition id of a competition to cancel
     */
    public void open(long idCompetition) {
        tournamentRepository
                .findById(idCompetition)
                .ifPresent(competition -> {
                    competition.setTournamentState(TournamentState.OPEN);
                    tournamentRepository.save(competition);
                });
    }

    public void setParticipantsNumber(Tournament tournament) {
        int number = StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .filter(p -> p.getTournament().getId() == tournament.getId())
                .collect(Collectors.toList())
                .size();
        tournament.setAttendancesNumber(number);
    }
}
