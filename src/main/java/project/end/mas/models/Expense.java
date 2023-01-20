package project.end.mas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Expense {

    public Expense(@NotNull double cost, LocalDate billingDate, @NotNull int number, @NotNull Owner owner) {
        this.cost = cost;
        this.billingDate = billingDate;
        this.number = number;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    private long id;

    @NotNull
    private double cost;

    @Column(name = "billing_date")
    private LocalDate billingDate;

    @NotNull
    @Column(unique = true)
    private int number;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;
}
