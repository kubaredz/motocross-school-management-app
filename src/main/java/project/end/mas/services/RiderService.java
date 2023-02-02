package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Tournament;
import project.end.mas.models.Rider;

@Service
@RequiredArgsConstructor
public class RiderService {

    /**
     * <p> method checks if rider can compete in a chosen tournament </p>
     *
     * @param tournament selected tournament
     * @param rider      currently logged rider in application
     * @return verification that the rider can take part in the tournament
     */
    public boolean checkRiderCanTakePartInTournament(Tournament tournament, Rider rider) {
        int tournamentType = tournament.getTournamentType().ordinal();
        int riderStartingLeague = rider.getStartingLeague().ordinal();
        return riderStartingLeague >= tournamentType;
    }
}