package e2;

import java.util.*;

public record EuroCoin(NominalValue value, CoinColor color, Country country, String design, int year)
        implements Comparable<EuroCoin> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EuroCoin euroCoin)) return false;
        return value == euroCoin.value && color == euroCoin.color &&
                country == euroCoin.country && design.equals(euroCoin.design);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color, country, design);
    }

    @Override
    public int compareTo(EuroCoin other) {
        // Primero, compara por valor (de forma descendente)
        int valueComparison = Integer.compare(other.value().getValue(), this.value().getValue());
        if (valueComparison != 0) {
            return valueComparison;
        }

        // Si los valores son iguales, compara por país (de forma ascendente)
        int countryComparison = this.country().name().compareTo(other.country().name());
        if (countryComparison != 0) {
            return countryComparison;
        }

        // Si el país también es igual, compara por diseño (de forma ascendente)
        int designComparison = this.design().compareTo(other.design());
        if (designComparison != 0) {
            return designComparison;
        }

        // Si el diseño también es igual, compara por año (de forma ascendente)
        return Integer.compare(this.year(), other.year());
    }
}