package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "riders_club")
@NoArgsConstructor
@Getter
@Setter
public class RidersClub {

    public RidersClub(@NotNull String name, @NotNull String color) {
        this.name = name;
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_riders_club")
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ridersClub")
    private List<Rider> riders;



    public void addRider(Rider rider) {
        getRiders().add(rider);
        rider.setRidersClub(this);
    }

    public void removeRider(Rider rider) {
        getRiders().remove(rider);
        rider.setRidersClub(null);
    }
}
