package project.end.mas.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.end.mas.models.Tournament;
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
     * @param tournament selected competition
     * @return list of horses
     */
    public List<Motocross> showActiveHorses(Tournament tournament) {
        return StreamSupport
                .stream(motocrossRepository.findAll().spliterator(), false)
                .filter(Motocross::isWorking)
                .filter(h -> !horseInCompetition(tournament, h))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checking if a selected horse takes part in a chosen competition already </p>
     * @param tournament selected competition
     * @param motocross selected horse
     * @return true if selected horse already takes part in this competition
     */
    private boolean horseInCompetition(Tournament tournament, Motocross motocross) {
        return StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .anyMatch(p -> p.getTournament().equals(tournament) && p.getMotocross().equals(motocross));
    }
}
