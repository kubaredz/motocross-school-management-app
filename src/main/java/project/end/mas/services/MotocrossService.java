package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Competition;
import project.end.mas.models.Motocross;
import project.end.mas.repositories.MotocrossRepository;
import project.end.mas.repositories.AttendanceRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MotocrossService {

    private final MotocrossRepository motocrossRepository;

    private final AttendanceRepository attendanceRepository;

    /**
     * <p> method showing horses that are active and don't participate yet in a selected competition </p>
     * @param competition selected competition
     * @return list of horses
     */
    public List<Motocross> showActiveHorses(Competition competition) {
        return StreamSupport
                .stream(motocrossRepository.findAll().spliterator(), false)
                .filter(Motocross::isWorking)
                .filter(h -> !horseInCompetition(competition, h))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checking if a selected horse takes part in a chosen competition already </p>
     * @param competition selected competition
     * @param motocross selected horse
     * @return true if selected horse already takes part in this competition
     */
    private boolean horseInCompetition(Competition competition, Motocross motocross) {
        return StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .anyMatch(p -> p.getCompetition().equals(competition) && p.getMotocross().equals(motocross));
    }
}
