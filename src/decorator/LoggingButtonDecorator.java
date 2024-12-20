/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author Mahmoud
 */
public class LoggingButtonDecorator extends ButtonDecorator {
    public LoggingButtonDecorator(Component wrappedComponent) {
        super(wrappedComponent);
    }

    @Override
    public void render() {
        System.out.println("Logging: Button clicked!");
        super.render();  // Call the base render method
    }
}
