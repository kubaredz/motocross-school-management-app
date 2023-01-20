package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "training_action")
@NoArgsConstructor
@Getter
@Setter
public class TrainingAction {

    public TrainingAction(@NotNull String name, @NotNull int level) {
        this.name = name;
        this.level = level;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training_action")
    private long id;

    @NotNull
    private String name;

    @NotNull
    private int level;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingAction")
    private List<Training> trainings;



    public void addTraining(Training training) {
        getTrainings().add(training);
        training.setTrainingAction(this);
    }

    public void removeTraining(Training training) {
        getTrainings().remove(training);
        training.setTrainingAction(null);
    }
}
