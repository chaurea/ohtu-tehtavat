
package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoInter {
    void lisaaTapahtuma(String tapahtuma);
	ArrayList<String> getTapahtumat();
}
