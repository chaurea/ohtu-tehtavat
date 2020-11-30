  
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komentotehdas {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);                
    }

    @Override
    public void suorita() {
        this.aloitus = Integer.valueOf(tuloskentta.getText());
        sovellus.nollaa();
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
    }  
    
}