package e4;
import java.util.*;


public class EuroCoinCollection {
    private final Set<EuroCoin> collection;

    public EuroCoinCollection() {
        this.collection = new HashSet<>();
    }

    // Añadir una moneda a la colección
    public boolean addCoin(EuroCoin coin) {
        return collection.add(coin); // Solo se añade si no está repetida
    }

    // Eliminar una moneda de la colección
    public boolean removeCoin(EuroCoin coin) {
        return collection.remove(coin);
    }

    // Contar el número de monedas en la colección
    public int getCoinCount() {
        return collection.size();
    }

    // Calcular el valor nominal total de las monedas en la colección
    public int getTotalValue() {
        return collection.stream().mapToInt(coin -> coin.value().getValue()).sum();
    }

    // Comprobar si una moneda está en la colección
    public boolean containsCoin(EuroCoin coin) {
        return collection.contains(coin);
    }

    // Mostrar todas las monedas de la colección
    public String showCollection() {
        if (collection.isEmpty()) {
            return "The collection is empty.";
        }
        StringBuilder sb = new StringBuilder();
        for (EuroCoin coin : collection) {
            sb.append(coin.toString()).append("\n");
        }
        return sb.toString();
    }
}
