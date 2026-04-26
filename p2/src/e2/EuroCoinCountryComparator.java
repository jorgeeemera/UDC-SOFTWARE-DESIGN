package e2;

import java.util.Comparator;

public class EuroCoinCountryComparator implements Comparator<EuroCoin> {
    @Override
    public int compare(EuroCoin coin1, EuroCoin coin2) {
        // First compare by country code (ascending)
        int countryComparison = coin1.country().name().compareTo(coin2.country().name());
        if (countryComparison != 0) {
            return countryComparison;
        }

        // For same country, compare by value (descending)
        int valueComparison = Integer.compare(coin2.value().getValue(), coin1.value().getValue());
        if (valueComparison != 0) {
            return valueComparison;
        }

        // For same value and country, compare by year (ascending)
        return Integer.compare(coin1.year(), coin2.year());
    }
}