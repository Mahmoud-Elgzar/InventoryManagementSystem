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
public class simplebutton implements Component {
    private String label;

    public simplebutton(String label) {
        this.label = label;
    }

    @Override
    public void render() {
        System.out.println("Rendering button: " + label);
        // Logic to render button in GUI (for simplicity, printing it here)
    }
}

