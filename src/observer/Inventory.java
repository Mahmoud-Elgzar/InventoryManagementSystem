package observer;

import java.util.ArrayList;
import java.util.List;
import models.Product;

public class Inventory {
    private static Inventory instance;
    private final List<Observer> observers = new ArrayList<>();
    private List<Product> products;

    private Inventory() { }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
        public List<Product> getProducts() {
        return products;
    }
}
