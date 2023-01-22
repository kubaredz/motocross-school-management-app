package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Tournament;
import project.end.mas.models.Rider;

@Service
@RequiredArgsConstructor
public class RiderService {

    /**
     * <p> method checks if rider can compete in a chosen competition </p>
     * @param tournament selected competition
     * @param rider currently logged rider in application
     * @return true if rider has >= stars then chooses competition
     */
    public boolean checkStars(Tournament tournament, Rider rider) {
        int competitionStars = tournament.getTournamentType();
        int riderStars = rider.getStartingLeague();
        return riderStars >= competitionStars;
    }
}
