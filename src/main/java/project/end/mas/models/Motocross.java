package project.end.mas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline", discriminatorType = DiscriminatorType.STRING)
public class Motocross {

    public Motocross(@NotNull String name, String nickname, @NotNull LocalDate birthday, @NotNull String color, @NotNull boolean isActive, @NotNull Owner owner) {
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.color = color;
        this.isActive = isActive;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motocross")
    private long id;

    @NotNull
    private String name;

    private String nickname;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private String color;

    @NotNull
    @Column(name = "is_active")
    private boolean isActive;

    @Transient
    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Participation> participations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Training> trainings;



    public void addParticipation(Participation participation) {
        getParticipations().add(participation);
        participation.setMotocross(this);
    }

    public void removeParticipation(Participation participation) {
        getParticipations().remove(participation);
        participation.setMotocross(null);
    }

    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setMotocross(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setMotocross(null);
    }
}
