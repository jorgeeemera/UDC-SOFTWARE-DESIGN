package e4;
import java.util.*;

public record EuroCoin(NominalValue value, CoinColor color, Country country, String design, int year) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EuroCoin euroCoin)) return false;
        return value == euroCoin.value && color == euroCoin.color &&
                country == euroCoin.country && design.equals(euroCoin.design);
    }

    // El hashCode sigue utilizando los atributos relevantes
    @Override
    public int hashCode() {
        return Objects.hash(value, color, country, design);
    }
}

