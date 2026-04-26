package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EuroCoinCollectionTest {
    private EuroCoinCollection collection;
    private EuroCoin coin1;
    private EuroCoin coin2;
    private EuroCoin coin3;
    private EuroCoin coin4;
    private EuroCoin coin5;

    @BeforeEach
    public void setUp() {
        collection = new EuroCoinCollection();
        // DE (Germany) - 2€
        coin1 = new EuroCoin(NominalValue.TWO_EUROS, CoinColor.GOLD_SILVER, Country.DE, "Brandenburg Gate", 2019);
        // FR (France) - 1€
        coin2 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Eiffel Tower", 2021);
        // IT (Italy) - 0.50€
        coin3 = new EuroCoin(NominalValue.FIFTY_CENTS, CoinColor.BRONZE, Country.IT, "Colosseum", 2020);
        // FR (France) - 2€
        coin4 = new EuroCoin(NominalValue.TWO_EUROS, CoinColor.GOLD_SILVER, Country.FR, "Liberty", 2018);
        // Duplicate of coin2
        coin5 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Eiffel Tower", 2021);

        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);
    }

    @Test
    public void testAddCoin() {
        assertTrue(collection.addCoin(coin4));
        assertEquals(4, collection.getCoinCount());

        assertFalse(collection.addCoin(coin5), "Should not add duplicate coin");
        assertEquals(4, collection.getCoinCount());
    }

    @Test
    public void testRemoveCoin() {
        assertTrue(collection.removeCoin(coin1));
        assertEquals(2, collection.getCoinCount());

        assertFalse(collection.removeCoin(coin4));
        assertEquals(2, collection.getCoinCount());
    }

    @Test
    public void testGetCoinCount() {
        assertEquals(3, collection.getCoinCount());
    }

    @Test
    public void testGetTotalValue() {
        // 2€ + 1€ + 0.50€ = 350 cents
        int expectedValue = 350;
        assertEquals(expectedValue, collection.getTotalValue());
    }

    @Test
    public void testContainsCoin() {
        assertTrue(collection.containsCoin(coin1));
        assertFalse(collection.containsCoin(coin4));
        assertTrue(collection.containsCoin(coin5), "Should find coin with same properties");
    }

    @Test
    public void testSortByNaturalOrder() {
        List<EuroCoin> sortedCoins = collection.sortByNaturalOrder();

        // Natural order: value (desc), country (asc), design (asc), year (asc)
        assertEquals(coin1, sortedCoins.get(0)); // 2€ DE
        assertEquals(coin2, sortedCoins.get(1)); // 1€ FR
        assertEquals(coin3, sortedCoins.get(2)); // 0.50€ IT
    }

    @Test
    public void testSortWithComparator() {
        collection.addCoin(coin4); // Adding FR 2€ coin for better testing
        List<EuroCoin> sortedCoins = collection.sortWithComparator(new EuroCoinCountryComparator());

        // Order by: country (asc), value (desc), year (asc)
        assertEquals(Country.DE, sortedCoins.get(0).country()); // Germany first
        assertEquals(Country.FR, sortedCoins.get(1).country()); // France second with 2€
        assertEquals(Country.FR, sortedCoins.get(2).country()); // France third with 1€
        assertEquals(Country.IT, sortedCoins.get(3).country()); // Italy last

        // Check that for same country (FR), higher value comes first
        assertEquals(NominalValue.TWO_EUROS, sortedCoins.get(1).value());
        assertEquals(NominalValue.ONE_EURO, sortedCoins.get(2).value());
    }

    @Test
    public void testShowCollectionEmpty() {
        EuroCoinCollection emptyCollection = new EuroCoinCollection();
        assertEquals("The collection is empty.", emptyCollection.showCollection());
    }

    @Test
    public void testShowCollection() {
        String output = collection.showCollection();
        assertTrue(output.contains("Brandenburg Gate"));
        assertTrue(output.contains("Eiffel Tower"));
        assertTrue(output.contains("Colosseum"));
    }

    @Test
    public void testNaturalOrdering() {
        // Test value comparison (descending)
        assertTrue(coin1.compareTo(coin2) < 0, "Higher value should come first");
        assertTrue(coin2.compareTo(coin3) < 0, "Higher value should come first");

        // Test country comparison (ascending) when values are equal
        EuroCoin deOneCoin = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.DE, "Test", 2020);
        EuroCoin frOneCoin = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Test", 2020);
        assertTrue(deOneCoin.compareTo(frOneCoin) < 0, "DE should come before FR");

        // Test design comparison when value and country are equal
        EuroCoin coin1Design = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "A", 2020);
        EuroCoin coin2Design = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "B", 2020);
        assertTrue(coin1Design.compareTo(coin2Design) < 0, "Design A should come before B");

        // Test year comparison when all else is equal
        EuroCoin coin2018 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Test", 2018);
        EuroCoin coin2019 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Test", 2019);
        assertTrue(coin2018.compareTo(coin2019) < 0, "Earlier year should come first");
    }

    @Test
    public void testCountryComparator() {
        EuroCoinCountryComparator comparator = new EuroCoinCountryComparator();

        // Test country ordering
        assertTrue(comparator.compare(coin1, coin2) < 0, "DE should come before FR");
        assertTrue(comparator.compare(coin2, coin3) < 0, "FR should come before IT");

        // Test value ordering within same country
        assertTrue(comparator.compare(coin4, coin2) < 0, "For same country, higher value first");

        // Test year ordering within same country and value
        EuroCoin fr2018 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Test", 2018);
        EuroCoin fr2019 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.FR, "Test", 2019);
        assertTrue(comparator.compare(fr2018, fr2019) < 0, "Earlier year should come first");
    }

    // Additional tests to improve branch coverage

    @Test
    public void testAddDuplicateCoin() {
        // Agregar monedas duplicadas
        assertFalse(collection.addCoin(coin5)); // coin5 es un duplicado de coin2
        assertEquals(3, collection.getCoinCount()); // La colección no cambia
    }

    @Test
    public void testRemoveCoinThatDoesNotExist() {
        // Intentar eliminar una moneda no existente
        EuroCoin coinThatDoesNotExist = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD, Country.IT, "Fake Design", 2022);
        assertFalse(collection.removeCoin(coinThatDoesNotExist)); // No debe poder eliminarla
    }

    @Test
    public void testGetTotalValueWithEmptyCollection() {
        EuroCoinCollection emptyCollection = new EuroCoinCollection();
        assertEquals(0, emptyCollection.getTotalValue()); // El total debe ser 0 en una colección vacía
    }
}