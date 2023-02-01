package project.end.mas.enums;

public enum TournamentType {
    KLB("Klubowe", 1),
    REG("Regionalne", 2),
    POL("Ogolnopolskie", 3),
    GLOBAL("Miedzynarodowe", 4);

    private String description;
    private int level;

    TournamentType(String description, int level) {
        this.description = description;
        this.level = level;
    }
}
