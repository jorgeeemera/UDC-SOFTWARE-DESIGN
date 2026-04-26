package e2;

enum NominalValue {
    ONE_CENT(1), TWO_CENTS(2), FIVE_CENTS(5),
    TEN_CENTS(10), TWENTY_CENTS(20), FIFTY_CENTS(50),
    ONE_EURO(100), TWO_EUROS(200);

    private final int value;

    NominalValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}