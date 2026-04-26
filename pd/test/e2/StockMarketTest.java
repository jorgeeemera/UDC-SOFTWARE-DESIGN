package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StockMarketTest {
    private StockMarket market;
    private SimpleTickerClient tickerClient;
    private DetailedClient detailedClient;
    private SpecificStockClient specificClient;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Configurar captura de salida de consola
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Inicializar objetos para las pruebas
        market = new StockMarket();
        tickerClient = new SimpleTickerClient("AAPL", "MSFT");
        detailedClient = new DetailedClient();
        specificClient = new SpecificStockClient("AAPL");
    }

    @Test
    void testStockDataGetters() {
        StockData data = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);

        assertEquals("AAPL", data.getSymbol());
        assertEquals(150.25, data.getClosePrice());
        assertEquals(152.00, data.getMaxPrice());
        assertEquals(149.50, data.getMinPrice());
        assertEquals(1000000, data.getVolume());
    }

    @Test
    void testInsertRemoveObservers() {
        market.insert(tickerClient);
        market.insert(detailedClient);

        // Verificar que los observadores reciben actualizaciones
        StockData data = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);
        market.updateStockData(data);

        String output = outputStream.toString();
        assertTrue(output.contains("TICKER"));
        assertTrue(output.contains("Detailed Info"));

        // Limpiar el stream para la siguiente prueba
        outputStream.reset();

        // Verificar que después de desconectar no reciben actualizaciones
        market.remove(tickerClient);
        market.updateStockData(data);

        output = outputStream.toString();
        assertFalse(output.contains("TICKER"));
        assertTrue(output.contains("Detailed Info"));
    }

    @Test
    void testSimpleTickerClient() {
        SimpleTickerClient client = new SimpleTickerClient("AAPL");
        StockData data = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);

        client.update(data);
        String output = outputStream.toString().trim(); // Importante: trim() para eliminar espacios en blanco

        String expectedOutput = String.format("TICKER - AAPL: $%.2f", 150.25);
        assertTrue(output.contains(expectedOutput),
                "Expected output: '" + expectedOutput + "' but was: '" + output + "'");

        // Verificar que no muestra actualizaciones de símbolos no suscritos
        outputStream.reset();
        StockData msftData = new StockData("MSFT", 290.50, 292.75, 289.25, 750000);
        client.update(msftData);
        output = outputStream.toString().trim();
        assertEquals("", output, "Should not output anything for non-subscribed symbol");
    }

    @Test
    void testDetailedClient() {
        DetailedClient client = new DetailedClient();
        StockData data = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);

        client.update(data);
        String output = outputStream.toString().trim();

        String expectedOutput = String.format("""
        Detailed Info for AAPL:
        Close: $%.2f
        Max: $%.2f
        Min: $%.2f
        Volume: %d
        ----------------------------------------""",
                150.25, 152.00, 149.50, 1000000);

        assertEquals(expectedOutput.trim(), output,
                "Output doesn't match expected format");
    }

    @Test
    void testSpecificStockClient() {
        SpecificStockClient client = new SpecificStockClient("AAPL");
        StockData appleData = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);
        StockData msftData = new StockData("MSFT", 290.50, 292.75, 289.25, 750000);

        // Verificar que recibe actualizaciones del símbolo específico
        client.update(appleData);
        String output = outputStream.toString();
        assertTrue(output.contains("Update for AAPL"));

        // Verificar que ignora otros símbolos
        outputStream.reset();
        client.update(msftData);
        output = outputStream.toString();
        assertTrue(output.isEmpty());
    }

    @Test
    void testMultipleUpdates() {
        market.insert(tickerClient);
        market.insert(detailedClient);
        market.insert(specificClient);

        StockData appleData = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);
        StockData msftData = new StockData("MSFT", 290.50, 292.75, 289.25, 750000);

        market.updateStockData(appleData);
        market.updateStockData(msftData);

        String output = outputStream.toString();

        // Verificar que todos los observadores recibieron sus actualizaciones correspondientes
        assertTrue(output.contains("TICKER - AAPL"));
        assertTrue(output.contains("TICKER - MSFT"));
        assertTrue(output.contains("Detailed Info for AAPL"));
        assertTrue(output.contains("Detailed Info for MSFT"));
        assertTrue(output.contains("Update for AAPL"));
        // Verificar que SpecificStockClient no mostró info de MSFT
        assertFalse(output.contains("Update for MSFT"));
    }

    @Test
    void testEmptySymbolList() {
        SimpleTickerClient emptyClient = new SimpleTickerClient();
        StockData data = new StockData("AAPL", 150.25, 152.00, 149.50, 1000000);

        emptyClient.update(data);
        String output = outputStream.toString();

        // Un SimpleTickerClient sin símbolos específicos debería mostrar todas las actualizaciones
        assertTrue(output.contains("TICKER"));
        assertTrue(output.contains("AAPL"));
    }

    @Test
    void testEdgeCases() {
        // Verificar que no se producen excepciones con valores extremos
        StockData edgeData = new StockData("TEST",
                Double.MAX_VALUE,
                Double.MAX_VALUE,
                Double.MIN_VALUE,
                Integer.MAX_VALUE);

        assertDoesNotThrow(() -> {
            market.insert(tickerClient);
            market.updateStockData(edgeData);
        });

        // Verificar que el detach de un observer no registrado no causa problemas
        Observer nonRegisteredObserver = new SimpleTickerClient();
        assertDoesNotThrow(() -> market.remove(nonRegisteredObserver));
    }
}
