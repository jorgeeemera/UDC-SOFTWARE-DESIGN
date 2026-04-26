package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

class EuroCoinCollectionTest {

    private EuroCoinCollection collection;
    private EuroCoin euro1;
    private EuroCoin euro2;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection();
        euro1 = new EuroCoin(NominalValue.EURO1, CoinColor.GOLD, Country.ES, "Design 1", 2022);
        euro2 = new EuroCoin(NominalValue.EURO2, CoinColor.GOLD, Country.FR, "Design 2", 2021);
        collection.addCoin(euro1);
        collection.addCoin(euro2);
    }

    @Test
    void addCoin() {
        EuroCoin newCoin = new EuroCoin(NominalValue.CENT50, CoinColor.BRONZE, Country.IT, "Design 3", 2020);
        assertTrue(collection.addCoin(newCoin));
        assertFalse(collection.addCoin(euro1)); // Moneda repetida no se agrega
    }

    @Test
    void removeCoin() {
        assertTrue(collection.removeCoin(euro1));
        assertFalse(collection.removeCoin(euro1)); // Intentar eliminar una moneda que ya no está
    }

    @Test
    void iterator() {
        Iterator<EuroCoin> iterator = collection.iterator(Country.ES);
        assertTrue(iterator.hasNext());
        assertEquals(euro1, iterator.next());
        assertFalse(iterator.hasNext()); // Solo debería haber una moneda de ES en la iteración

        iterator = collection.iterator(null); // Prueba iterando con país null
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorFailFast() {
        Iterator<EuroCoin> iterator = collection.iterator(null);
        collection.addCoin(new EuroCoin(NominalValue.CENT10, CoinColor.BRONZE, Country.DE, "Design 4", 2019));
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void testIteratorRemove() {
        Iterator<EuroCoin> iterator = collection.iterator(Country.FR);
        assertTrue(iterator.hasNext());
        EuroCoin coin = iterator.next();
        iterator.remove(); // Eliminar la última moneda obtenida
        assertFalse(collection.containsCoin(coin));

        assertThrows(IllegalStateException.class, iterator::remove); // Intentar eliminar dos veces
    }

    @Test
    void testNoSuchElementException() {
        Iterator<EuroCoin> iterator = collection.iterator(Country.IT);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos objetos EuroCoin iguales y uno diferente
        EuroCoin coin1 = new EuroCoin(NominalValue.EURO1, CoinColor.GOLD, Country.ES, "Design A", 2020);
        EuroCoin coin2 = new EuroCoin(NominalValue.EURO1, CoinColor.GOLD, Country.ES, "Design A", 2020);
        EuroCoin coin3 = new EuroCoin(NominalValue.EURO2, CoinColor.GOLD_SILVER, Country.FR, "Design B", 2021);

        // Verificar que coin1 y coin2 son iguales y tienen el mismo hashCode
        assertEquals(coin1, coin2);
        assertEquals(coin1.hashCode(), coin2.hashCode());

        // Verificar que coin1 y coin3 no son iguales y tienen diferente hashCode
        assertNotEquals(coin1, coin3);
        assertNotEquals(coin1.hashCode(), coin3.hashCode());
    }

    @Test
    void testNotEqualsDifferentType() {
        // Verificar que un EuroCoin no es igual a un objeto de otro tipo
        EuroCoin coin = new EuroCoin(NominalValue.EURO1, CoinColor.GOLD, Country.ES, "Design A", 2020);
        String notCoin = "Not a coin";
        assertNotEquals(coin, notCoin);
    }

    @Test
    void testCountryValues() {
        // Verificar que todos los valores de Country existen
        assertEquals(24, Country.values().length);
        assertEquals("Andorra", Country.AD.getCountryName());
        assertEquals("Spain", Country.ES.getCountryName());
    }

    @Test
    void testGetCountryName() {
        // Verificar que el método getCountryName funciona correctamente
        Country country = Country.FR;
        assertEquals("France", country.getCountryName());
    }

    @Test
    void testCoinColorValues() {
        // Verificar que los valores de CoinColor existen
        assertEquals(3, CoinColor.values().length);
        assertEquals(CoinColor.GOLD, CoinColor.valueOf("GOLD"));
        assertEquals(CoinColor.BRONZE, CoinColor.valueOf("BRONZE"));
        assertEquals(CoinColor.GOLD_SILVER, CoinColor.valueOf("GOLD_SILVER"));
    }

    @Test
    void testNominalValueValues() {
        // Verificar que los valores de NominalValue existen
        assertEquals(8, NominalValue.values().length);
        assertEquals(1, NominalValue.CENT1.getValue());
        assertEquals(200, NominalValue.EURO2.getValue());
    }

    @Test
    void testGetValue() {
        // Verificar que el método getValue funciona correctamente
        NominalValue nominalValue = NominalValue.CENT20;
        assertEquals(20, nominalValue.getValue());
    }
}
