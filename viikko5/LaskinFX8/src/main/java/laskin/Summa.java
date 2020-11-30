  
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komentotehdas {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);                
    }

    @Override
    public void suorita() {
        this.aloitus = Integer.valueOf(tuloskentta.getText());
        sovellus.plus(Integer.valueOf(syotekentta.getText()));
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
    }  
    
}