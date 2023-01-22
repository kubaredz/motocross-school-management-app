package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;
import project.end.mas.enums.TournamentState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class Tournament {

    public Tournament(@NotNull String name, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull int prizePool, @NotNull @Range(min = 1, max = 4) int tournamentType, @NotNull TournamentState tournamentState) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prizePool = prizePool;
        this.tournamentType = tournamentType;
        this.tournamentState = tournamentState;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tournament")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "prize_pool")
    private int prizePool;

    @NotNull
    @Range(min = 1, max = 4)
    @Column(name = "tournament_type")
    private int tournamentType;

    @Transient
    private int attendancesNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'OPEN'")
    private TournamentState tournamentState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)
    private List<Attendance> attendances;


    public void addParticipation(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setTournament(this);
    }

    public void removeParticipation(Attendance attendance) {
        getAttendances().remove(attendance);
        attendance.setTournament(null);
    }

}
