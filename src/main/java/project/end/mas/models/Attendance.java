package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Attendance {

    public Attendance(@NotNull Rider rider, @NotNull Motocross motocross, @NotNull Competition competition) {
        this.rider = rider;
        this.motocross = motocross;
        this.competition = competition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attendance")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rider")
    private Rider rider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_motocross")
    private Motocross motocross;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;

}
