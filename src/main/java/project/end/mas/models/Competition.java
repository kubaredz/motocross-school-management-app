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
public class Competition {

    public Competition(@NotNull String showName, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull int budget, @NotNull @Range(min = 1, max = 4) int numberOfStars, @NotNull TournamentState state) {
        this.showName = showName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.numberOfStars = numberOfStars;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competition")
    private long id;

    @NotNull
    @Column(name = "show_name")
    private String showName;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    private int budget;

    @NotNull
    @Range(min = 1, max = 4)
    @Column(name = "number_of_stars")
    private int numberOfStars;

    @Transient
    private int participantsNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'OPEN'")
    private TournamentState state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition", orphanRemoval = true)
    private List<Participation> participations;



    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setCompetition(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setCompetition(null);
    }

}
