package e2;

// Clase para almacenar los datos de una acción
public class StockData {
    private String symbol;
    private double closePrice;
    private double maxPrice;
    private double minPrice;
    private int volume;

    public StockData(String symbol, double closePrice, double maxPrice, double minPrice, int volume) {
        this.symbol = symbol;
        this.closePrice = closePrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.volume = volume;
    }

    // Getters
    public String getSymbol() { return symbol; }
    public double getClosePrice() { return closePrice; }
    public double getMaxPrice() { return maxPrice; }
    public double getMinPrice() { return minPrice; }
    public int getVolume() { return volume; }
}
