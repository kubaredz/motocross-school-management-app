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
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Motocross {

    public Motocross(@NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, @NotNull LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner) {
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.motorPower = motorPower;
        this.serviceDate = serviceDate;
        this.isWorking = isWorking;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motocross")
    private long id;

    @NotNull
    private String brand;

    private String model;

    @NotNull
    private LocalDate productionDate;

    @NotNull
    private double motorPower;

    private LocalDate serviceDate;

    @NotNull
    @Column(name = "is_working")
    private boolean isWorking;

    @Transient
    public int getMileage() {
        return Period.between(getProductionDate(), LocalDate.now()).getYears();
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Attendance> attendances;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Training> trainings;

    public void addParticipation(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setMotocross(this);
    }

    public void removeParticipation(Attendance attendance) {
        getAttendances().remove(attendance);
        attendance.setMotocross(null);
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