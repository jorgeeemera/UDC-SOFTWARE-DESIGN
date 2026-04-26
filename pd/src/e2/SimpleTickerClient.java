package e2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Cliente que solo muestra el precio de cierre
public class SimpleTickerClient implements Observer {
    private Set<String> interestedSymbols = new HashSet<>();

    public SimpleTickerClient(String... symbols) {
        interestedSymbols.addAll(Arrays.asList(symbols));
    }

    @Override
    public void update(StockData data) {
        if (interestedSymbols.isEmpty() || interestedSymbols.contains(data.getSymbol())) {
            displayClosingPrice(data);
        }
    }

    private void displayClosingPrice(StockData data) {
        System.out.printf("TICKER - %s: $%.2f\n", data.getSymbol(), data.getClosePrice());
    }
}
