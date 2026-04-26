package e3;

enum NominalValue {
    CENT1(1), CENT2(2), CENT5(5),
    CENT10(10), CENT20(20), CENT50(50),
    EURO1(100), EURO2(200);

    private final int value;

    NominalValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}