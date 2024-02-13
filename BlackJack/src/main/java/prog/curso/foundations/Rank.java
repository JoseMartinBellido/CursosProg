package prog.curso.foundations;

public enum Rank {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    // Attribute
    private int value;

    // Constructor
    Rank(int value) {
        this.value = value;
    }

    // Getter
    public int getValue() {
        return value;
//        return switch (this) {
//            case JACK, QUEEN, KING ->  10;
//            default -> ordinal() + 1;
//        };
//        return (this == Rank.JACK || this == Rank.KING || this == Rank.QUEEN)
//                ? 10 : ordinal() + 1;
    }
}
