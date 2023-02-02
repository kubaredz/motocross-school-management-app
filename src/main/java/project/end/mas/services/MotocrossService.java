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
     * <p> method showing motocrosses that are active and don't attend yet in a selected tournament </p>
     *
     * @param tournament selected tournament
     * @return list of motocrosses
     */
    public List<Motocross> showActiveMotocrosses(Tournament tournament) {
        return StreamSupport
                .stream(motocrossRepository.findAll().spliterator(), false)
                .filter(Motocross::isWorking)
                .filter(motocross -> !motocrossInTournament(tournament, motocross))
                .collect(Collectors.toList());
    }

    /**
     * <p> method checking if a selected motocross takes part in a chosen tournament already </p>
     *
     * @param tournament selected tournament
     * @param motocross  selected motocross
     * @return true if selected motocross already takes part in this tournament
     */
    private boolean motocrossInTournament(Tournament tournament, Motocross motocross) {
        return StreamSupport
                .stream(attendanceRepository.findAll().spliterator(), false)
                .anyMatch(p -> p.getTournament().equals(tournament) && p.getMotocross().equals(motocross));
    }
}