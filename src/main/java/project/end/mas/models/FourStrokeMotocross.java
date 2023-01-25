package project.end.mas.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "four_stroke_motocross")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("FOUR_STROKE")
public class FourStrokeMotocross extends Motocross {

    public FourStrokeMotocross(long id, @NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner, List<Attendance> attendances, List<Training> trainings, @NotNull String valveType, @NotNull String fuelPumpType, @NotNull LocalDate timingSystemReplacementDate) {
        super(id, brand, model, productionDate, motorPower, serviceDate, isWorking, owner, attendances, trainings);
        this.valveType = valveType;
        this.fuelPumpType = fuelPumpType;
        this.timingSystemReplacementDate = timingSystemReplacementDate;
    }

    @NotNull
    @Column(name = "valve_type")
    private String valveType;

    @NotNull
    @Column(name = "fuel_pump_type")
    private String fuelPumpType;

    @NotNull
    @Column(name = "timing_system_replacement_date")
    private LocalDate timingSystemReplacementDate;
}