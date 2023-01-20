package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Rider {

    public Rider(@NotNull int feiId, @NotNull @Range(min = 1, max = 4) int highestClassAllowed, @NotNull Person person, RidersClub ridersClub) {
        this.feiId = feiId;
        this.highestClassAllowed = highestClassAllowed;
        this.person = person;
        this.ridersClub = ridersClub;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rider")
    private long id;

    @NotNull
    @Column(name = "fei_id", unique = true)
    private int feiId;

    @NotNull
    @Range(min = 1, max = 4)
    @Column(name = "highest_class_allowed")
    private int highestClassAllowed;

    //parnet-child association (orphanRemoval)
    @NotNull
    @OneToOne(mappedBy = "rider", orphanRemoval = true)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_riders_club")
    private RidersClub ridersClub;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Participation> participations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rider")
    private List<Training> trainings;



    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setRider(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setRider(null);
    }

    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setRider(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setRider(null);
    }

}
