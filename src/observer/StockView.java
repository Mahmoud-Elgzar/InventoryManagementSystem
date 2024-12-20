package observer;

public class StockView implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Stock View Updated: " + message);
    }
}
