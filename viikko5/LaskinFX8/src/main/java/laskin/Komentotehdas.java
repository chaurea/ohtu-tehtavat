package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komentotehdas {
    protected Sovelluslogiikka sovellus;
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected int aloitus;

    public Komentotehdas(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public void peru() {
        this.sovellus.undo();
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + aloitus);
    }

    public abstract void suorita(); 
}