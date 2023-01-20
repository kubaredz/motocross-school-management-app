package project.end.mas.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "dressage_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DRESSAGE")
public class DressageMotocross extends Motocross {

    public DressageMotocross(long id, @NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner, List<Participation> participations, List<Training> trainings, int highestPointsResult) {
        super(id, brand, model, productionDate, motorPower, serviceDate, isWorking, owner, participations, trainings);
        this.highestPointsResult = highestPointsResult;
    }

    @NotNull
    @Range(min = 0, max = 100)
    @Column(name = "highest_points_result")
    private int highestPointsResult;

    private static int minActiveYear = 2;

}
