package project.end.mas.models;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "dressage_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("DRESSAGE")
public class DressageMotocross extends Motocross {

    public DressageMotocross(@NotNull String name, String nickname, @NotNull LocalDate birthday, @NotNull String color, @NotNull boolean isActive, @NotNull Owner owner, @NotNull @Range(min = 0, max = 100) int highestPointsResult) {
        super(name, nickname, birthday, color, isActive, owner);
        this.highestPointsResult = highestPointsResult;
    }

    @NotNull
    @Range(min = 0, max = 100)
    @Column(name = "highest_points_result")
    private int highestPointsResult;

    private static int minActiveYear = 2;

}
