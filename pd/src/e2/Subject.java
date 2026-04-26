package e2;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void notifyObservers(StockData data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
    public void insert(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }
}

