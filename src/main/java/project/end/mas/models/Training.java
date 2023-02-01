package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Training {

    public Training(@NotNull LocalDate date, @NotNull @Range(min = 1, max = 10) int priority, @NotNull String description, @NotNull boolean isExecuted, @NotNull Rider rider, @NotNull Motocross motocross) {
        this.date = date;
        this.priority = priority;
        this.description = description;
        this.isExecuted = isExecuted;
        this.rider = rider;
        this.motocross = motocross;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_training")
    private long id;

    @NotNull
    private LocalDate date;

    @NotNull
    @Range(min = 1, max = 10)
    private int priority;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "is_executed")
    private boolean isExecuted;

    //0..*-1
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    //0..*-1
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_motocross")
    private Motocross motocross;
}