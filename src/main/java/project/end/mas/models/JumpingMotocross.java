package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "jumping_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("JUMPING")
public class JumpingMotocross extends Motocross {

    public JumpingMotocross(long id, @NotNull String brand, String model, @NotNull LocalDate productionDate, @NotNull double motorPower, LocalDate serviceDate, @NotNull boolean isWorking, @NotNull Owner owner, List<Attendance> attendances, List<Training> trainings, float highestJump) {
        super(id, brand, model, productionDate, motorPower, serviceDate, isWorking, owner, attendances, trainings);
        this.highestJump = highestJump;
    }

    @NotNull
    @Column(name = "highest_jump")
    private float highestJump;

    private static int minActiveYear = 3;

}
