package e3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class EuroCoinCollection implements Iterable<EuroCoin> {
    private final Set<EuroCoin> collection;
    private int modCount; // Para rastrear modificaciones y garantizar fail-fast

    public EuroCoinCollection() {
        this.collection = new LinkedHashSet<>();
        this.modCount = 0;
    }

    // Añadir una moneda a la colección
    public boolean addCoin(EuroCoin coin) {
        boolean added = collection.add(coin);
        if (added) {
            modCount++; // Incrementa si se añade correctamente
        }
        return added;
    }

    // Eliminar una moneda de la colección
    public boolean removeCoin(EuroCoin coin) {
        boolean removed = collection.remove(coin);
        if (removed) {
            modCount++; // Incrementa si se elimina correctamente
        }
        return removed;
    }

    // Método para obtener el iterador con filtro de país
    @Override
    public Iterator<EuroCoin> iterator() {
        return new EuroCoinIterator(null); // Iterador sin filtro por defecto (devuelve todo)
    }

    public Iterator<EuroCoin> iterator(Country country) {
        return new EuroCoinIterator(country);
    }

    // Comprobar si una moneda está en la colección
    public boolean containsCoin(EuroCoin coin) {
        return collection.contains(coin);
    }


    // Clase interna para el iterador personalizado
    private class EuroCoinIterator implements Iterator<EuroCoin> {
        private final Iterator<EuroCoin> iterator;
        private final Country filterCountry;
        private EuroCoin lastReturned;
        private int expectedModCount;
        private EuroCoin nextCoin;
        private boolean nextCalled;

        public EuroCoinIterator(Country filterCountry) {
            this.iterator = collection.iterator();
            this.filterCountry = filterCountry;
            this.expectedModCount = modCount;
            this.nextCoin = advanceToNext();
            this.nextCalled = false;
        }

        private EuroCoin advanceToNext() {
            while (iterator.hasNext()) {
                EuroCoin coin = iterator.next();
                if (filterCountry == null || coin.country() == filterCountry) {
                    return coin;
                }
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            checkForModifications();
            return nextCoin != null;
        }

        @Override
        public EuroCoin next() {
            checkForModifications();
            if (nextCoin == null) {
                throw new NoSuchElementException();
            }
            lastReturned = nextCoin;
            nextCoin = advanceToNext();
            nextCalled = true;
            return lastReturned;
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException("next() has not been called, or remove() has already been called.");
            }
            checkForModifications();
            iterator.remove();
            expectedModCount++;
            nextCalled = false;
            modCount++;
        }

        private void checkForModifications() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
