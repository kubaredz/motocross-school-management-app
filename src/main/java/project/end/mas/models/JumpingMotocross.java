package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "jumping_horse")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("JUMPING")
public class JumpingMotocross extends Motocross {

    public JumpingMotocross(@NotNull String name, String nickname, @NotNull LocalDate birthday, @NotNull String color, @NotNull boolean isActive, @NotNull Owner owner, @NotNull float highestJump) {
        super(name, nickname, birthday, color, isActive, owner);
        this.highestJump = highestJump;
    }

    @NotNull
    @Column(name = "highest_jump")
    private float highestJump;

    private static int minActiveYear = 3;

}
