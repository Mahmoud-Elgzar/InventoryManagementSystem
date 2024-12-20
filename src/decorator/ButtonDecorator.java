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
public class ButtonDecorator implements Component {
    protected Component wrappedComponent;

    public ButtonDecorator(Component wrappedComponent) {
        this.wrappedComponent = wrappedComponent;
    }

    @Override
    public void render() {
        wrappedComponent.render();  // Delegate to the wrapped component
    }
}



