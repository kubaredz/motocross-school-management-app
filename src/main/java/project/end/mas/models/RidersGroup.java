package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "riders_group")
@NoArgsConstructor
@Getter
@Setter
public class RidersGroup {

    public RidersGroup(@NotNull String name, @NotNull LocalDate establishmentDate, @NotNull int numberOfMembers, @Range(min = 1, max = 3) int advancementLevel) {
        this.name = name;
        this.establishmentDate = establishmentDate;
        this.numberOfMembers = numberOfMembers;
        this.advancementLevel = advancementLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_riders_group")
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @Column(name = "number_of_members")
    private int numberOfMembers;

    @Range(min = 1, max = 3)
    @Column(name = "advancement_level")
    private int advancementLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ridersGroup")
    private List<Rider> riders;

    public void addRider(Rider rider) {
        getRiders().add(rider);
        rider.setRidersGroup(this);
    }

    public void removeRider(Rider rider) {
        getRiders().remove(rider);
        rider.setRidersGroup(null);
    }
}
