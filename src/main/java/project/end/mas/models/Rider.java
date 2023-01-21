package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rider {

    public Rider(@NotNull int licenseId, @Range(min = 1, max = 4) int startingLeague, @Range(min = 1, max = 99) int actualPlace, @NotNull LocalDate birthday, @NotNull Person person, RidersGroup ridersGroup) {
        this.licenseId = licenseId;
        this.startingLeague = startingLeague;
        this.actualPlace = actualPlace;
        this.birthday = birthday;
        this.person = person;
        this.ridersGroup = ridersGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rider")
    private long id;

    //{unique}
    @NotNull
    @Column(name = "license_id", unique = true)
    private int licenseId;

    @Range(min = 1, max = 4)
    @Column(name = "starting_league")
    private int startingLeague;

    @Range(min = 1, max = 99)
    @Column(name = "actual_place")
    private int actualPlace;

    @NotNull
    @Column(name = "birthday")
    private LocalDate birthday;

    //Atr. wyliczalny Age
    @Transient
    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    //parnet-child association (orphanRemoval)
    @NotNull
    @OneToOne(mappedBy = "rider", orphanRemoval = true)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_riders_group")
    private RidersGroup ridersGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Participation> participationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Training> trainingList;

    public void addParticipation(Participation participation) {
        getParticipationList().add(participation);
        participation.setRider(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipationList().remove(participation);
        participation.setRider(null);
    }

    public void addTraining(Training training) {
        getTrainingList().add(training);
        training.setRider(this);
    }

    public void removeTraining(Training training) {
        getTrainingList().remove(training);
        training.setRider(null);
    }
}