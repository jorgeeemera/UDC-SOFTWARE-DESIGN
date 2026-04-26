package e2;

import java.util.HashMap;
import java.util.Map;

// Cliente que muestra información detallada
public class DetailedClient implements Observer {
    private Map<String, StockData> stocksData = new HashMap<>();

    @Override
    public void update(StockData data) {
        stocksData.put(data.getSymbol(), data);
        displayFullInfo(data);
    }

    private void displayFullInfo(StockData data) {
        System.out.printf("Detailed Info for %s:\n", data.getSymbol());
        System.out.printf("Close: $%.2f\n", data.getClosePrice());
        System.out.printf("Max: $%.2f\n", data.getMaxPrice());
        System.out.printf("Min: $%.2f\n", data.getMinPrice());
        System.out.printf("Volume: %d\n", data.getVolume());
        System.out.println("----------------------------------------");
    }
}
