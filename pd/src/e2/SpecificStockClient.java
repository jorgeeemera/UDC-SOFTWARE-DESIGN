package e2;

// Cliente que observa acciones específicas
public class SpecificStockClient implements Observer {
    private String targetSymbol;

    public SpecificStockClient(String symbol) {
        this.targetSymbol = symbol;
    }

    @Override
    public void update(StockData data) {
        if (data.getSymbol().equals(targetSymbol)) {
            displaySpecificStock(data);
        }
    }

    private void displaySpecificStock(StockData data) {
        System.out.printf("Update for %s:\nCurrent Price: $%.2f\nToday's Range: $%.2f - $%.2f\nVolume: %d\n----------------------------------------\n",
                data.getSymbol(), data.getClosePrice(),
                data.getMinPrice(), data.getMaxPrice(),
                data.getVolume());
    }
}
