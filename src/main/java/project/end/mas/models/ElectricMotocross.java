package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("ELECTRIC")
@Entity(name = "electric_motocross")
public class ElectricMotocross extends Motocross {

    public ElectricMotocross(long id, @NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner, List<Attendance> attendances, List<Training> trainings, @NotNull float batteryCapacity) {
        super(id, brand, model, productionDate, motorPower, serviceDate, isWorking, owner, attendances, trainings);
        this.batteryCapacity = batteryCapacity;
    }

    @NotNull
    @Column(name = "battery_capacity")
    private float batteryCapacity;
}