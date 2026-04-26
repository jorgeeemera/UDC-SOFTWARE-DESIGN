package e4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EuroCoinCollectionTest {

    private EuroCoinCollection collection;
    private EuroCoin coin1;
    private EuroCoin coin2;
    private EuroCoin coin3;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection();
        coin1 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD_SILVER, Country.ES, "Juan Carlos I", 2001);
        coin2 = new EuroCoin(NominalValue.TWO_EUROS, CoinColor.GOLD_SILVER, Country.FR, "Eiffel Tower", 2010);
        coin3 = new EuroCoin(NominalValue.ONE_EURO, CoinColor.GOLD_SILVER, Country.ES, "Juan Carlos I", 2001); // duplicado de coin1
    }

    @AfterEach
    void tearDown() {
        collection = null;
    }

    @Test
    void addCoin() {
        assertTrue(collection.addCoin(coin1), "Should add the coin successfully");
        assertFalse(collection.addCoin(coin3), "Should not add duplicate coin"); // coin1 and coin3 are duplicates
        assertEquals(1, collection.getCoinCount(), "Should contain only one unique coin");
    }

    @Test
    void removeCoin() {
        collection.addCoin(coin1);
        assertTrue(collection.removeCoin(coin1), "Should remove the coin successfully");
        assertFalse(collection.removeCoin(coin1), "Should not remove the coin again"); // already removed
        assertEquals(0, collection.getCoinCount(), "Collection should be empty after removal");
    }

    @Test
    void getCoinCount() {
        assertEquals(0, collection.getCoinCount(), "Initially, the collection should be empty");
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertEquals(2, collection.getCoinCount(), "Should count two coins in the collection");
        collection.addCoin(coin3); // Adding a duplicate, should not count
        assertEquals(2, collection.getCoinCount(), "Should still count two coins due to duplicate");
    }

    @Test
    void getTotalValue() {
        assertEquals(0, collection.getTotalValue(), "Total value should be 0 for an empty collection");
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertEquals(300, collection.getTotalValue(), "Total value should be 300 cents (100 + 200)");
        collection.addCoin(coin3); // Adding a duplicate, value should not increase
        assertEquals(300, collection.getTotalValue(), "Total value should still be 300 cents due to duplicate");
    }

    @Test
    void containsCoin() {
        assertFalse(collection.containsCoin(coin1), "Should return false for a coin not in the collection");
        collection.addCoin(coin1);
        assertTrue(collection.containsCoin(coin1), "Should return true for a coin that is in the collection");
        assertFalse(collection.containsCoin(coin3), "Should return false for a duplicate coin that was not added");
    }

    @Test
    void showCollection() {
        assertEquals("The collection is empty.", collection.showCollection(), "Should indicate that the collection is empty");

        collection.addCoin(coin1);
        String expectedOutput = coin1.toString() + "\n";
        assertEquals(expectedOutput, collection.showCollection(), "Should display the coin in the collection");

        collection.addCoin(coin2);
        expectedOutput += coin2.toString() + "\n";
        assertEquals(expectedOutput, collection.showCollection(), "Should display both coins in the collection");
    }
}
