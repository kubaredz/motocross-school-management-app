package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Expense {

    public Expense(@NotNull int number, LocalDate billingDate, @NotNull double cost, @NotNull Owner owner) {
        this.number = number;
        this.billingDate = billingDate;
        this.cost = cost;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    private long id;

    //{unique}
    @NotNull
    @Column(unique = true)
    private int number;

    //[0..1]
    @Column(name = "billing_date")
    private LocalDate billingDate;

    @NotNull
    private double cost;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;
}