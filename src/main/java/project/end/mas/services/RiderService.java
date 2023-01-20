package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Competition;
import project.end.mas.models.Rider;

@Service
@RequiredArgsConstructor
public class RiderService {

    /**
     * <p> method checks if rider can compete in a chosen competition </p>
     * @param competition selected competition
     * @param rider currently logged rider in application
     * @return true if rider has >= stars then chooses competition
     */
    public boolean checkStars(Competition competition, Rider rider) {
        int competitionStars = competition.getNumberOfStars();
        int riderStars = rider.getHighestClassAllowed();
        return riderStars >= competitionStars;
    }
}
