package e2;

import java.util.HashMap;
import java.util.Map;

// Clase principal que gestiona el mercado de valores
public class StockMarket extends Subject {
    private Map<String, StockData> stocks = new HashMap<>();

    public void updateStockData(StockData data) {
        stocks.put(data.getSymbol(), data);
        notifyObservers(data);
    }
}

