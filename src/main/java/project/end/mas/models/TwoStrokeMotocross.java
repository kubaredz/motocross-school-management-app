package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "two_stroke_motocross")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("TWO_STROKE")
public class TwoStrokeMotocross extends Motocross {

    public TwoStrokeMotocross(long id, @NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner, List<Attendance> attendances, List<Training> trainings, @NotNull String fuelMixtureType) {
        super(id, brand, model, productionDate, motorPower, serviceDate, isWorking, owner, attendances, trainings);
        this.fuelMixtureType = fuelMixtureType;
    }

    @NotNull
    @Column(name = "fuel_mixture_type")
    private String fuelMixtureType;
}