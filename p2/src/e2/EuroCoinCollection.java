package e2;

import java.util.*;

public class EuroCoinCollection {
    private final Set<EuroCoin> collection;

    public EuroCoinCollection() {
        this.collection = new LinkedHashSet<>();
    }

    public boolean addCoin(EuroCoin coin) {
        return collection.add(coin);
    }

    public boolean removeCoin(EuroCoin coin) {
        return collection.remove(coin);
    }

    public int getCoinCount() {
        return collection.size();
    }

    public int getTotalValue() {
        return collection.stream().mapToInt(coin -> coin.value().getValue()).sum();
    }

    public boolean containsCoin(EuroCoin coin) {
        return collection.contains(coin);
    }

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

    // Método para ordenar la colección por orden natural
    public List<EuroCoin> sortByNaturalOrder() {
        List<EuroCoin> sortedList = new ArrayList<>(collection);
        Collections.sort(sortedList);
        return sortedList;
    }

    // Método para ordenar la colección utilizando un Comparator específico
    public List<EuroCoin> sortWithComparator(Comparator<EuroCoin> comparator) {
        List<EuroCoin> sortedList = new ArrayList<>(collection);
        sortedList.sort(comparator);
        return sortedList;
    }
}