package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import project.end.mas.enums.TournamentState;
import project.end.mas.enums.TournamentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@DynamicUpdate
@Getter
@Setter
@Entity
public class Tournament {

    public Tournament(@NotNull String name, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull int prizePool, TournamentType tournamentType, @NotNull TournamentState tournamentState) {
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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'NULL'", name = "tournament_type")
    private TournamentType tournamentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'OPEN'", name = "tournament_state")
    private TournamentState tournamentState;

    @Transient
    private int attendancesNumber;

    //1-0..*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", orphanRemoval = true)
    private List<Attendance> attendances;


    public void addAttendance(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setTournament(this);
    }

    public void removeAttendance(Attendance attendance) {
        getAttendances().remove(attendance);
        attendance.setTournament(null);
    }
}