package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import project.end.mas.enums.AdvancementLevel;
import project.end.mas.enums.TournamentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "riders_group")
public class RidersGroup {

    public RidersGroup(@NotNull String name, @NotNull LocalDate establishmentDate, @NotNull AdvancementLevel advancementLevel) {
        this.name = name;
        this.establishmentDate = establishmentDate;
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

    //Atrybut pochodny (wyliczalny)
    @Transient
    private int numberOfMembers() {
        return riders.size();
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'beginner'", name = "advancement_level")
    private AdvancementLevel advancementLevel;

    //0..1-*
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