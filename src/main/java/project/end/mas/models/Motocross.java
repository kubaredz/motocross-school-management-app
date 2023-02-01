package project.end.mas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Motocross {

    public Motocross(@NotNull String brand, @NotNull String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner) {
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

    @NotNull
    private String model;

    @NotNull
    @Column(name = "production_date")
    private LocalDate productionDate;

    @NotNull
    @Column(name = "motor_power")
    private double motorPower;

    @Column(name = "service_date")
    private LocalDate serviceDate;

    @NotNull
    @Column(name = "is_working")
    private boolean isWorking;

    //Atrybut pochodny (wyliczalny)
    @Transient
    public int getMileage() {
        return Period.between(getProductionDate(), LocalDate.now()).getYears();
    }

    //*-1
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    //1-0..*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Attendance> attendances;

    //1-0..*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motocross")
    private List<Training> trainings;

    public void addAttendance(Attendance attendance) {
        getAttendances().add(attendance);
        attendance.setMotocross(this);
    }

    public void removeAttendance(Attendance attendance) {
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