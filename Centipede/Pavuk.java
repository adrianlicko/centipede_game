import fri.shapesge.Obrazok;
import java.util.ArrayList;

/**
 * Pavuk, slúži pre zničenie vesmírnej lode.
 * 
 * @author Adrián Ličko
 */
public class Pavuk {
    private final Obrazok pavuk;
    private ArrayList<Smery> historiaPohybu;
    private int x, y;
    private int pocitadloZvisle, pocitadloVodorovne;
    private final TypPavuka typPavuka;
    
    /**
     * @param pa, dostáva enum, na základne ktorého sa zobrazí správny obrázok pavúka a nastáví sa je ho rýchlosť pri pohybe.
     * @param x
     * @param y
     */
    public Pavuk(TypPavuka pa, int x, int y) {
        // Zoznam všetkých posunov
        this.historiaPohybu = new ArrayList<Smery>();
        this.x = x;
        this.y = y;
        this.typPavuka = pa;
        this.pavuk = new Obrazok(this.typPavuka.getCestaKObrazku(), this.x, this.y);
        // Počítadlá slúžia na zadanie počtu koľko krát sa má pavúk kam pohnúť
        this.pocitadloZvisle = 0;
        this.pocitadloVodorovne = 0;
    }
    
    public int getPocitadloZvisle() {
        return this.pocitadloZvisle;
    }
    
    public int getPocitadloVodorovne() {
        return this.pocitadloVodorovne;
    }
    
    public void odpocitajPocitadloZvisle() {
        this.pocitadloZvisle -= 1;
    }
    
    public void pripocitajPocitadloZvisle() {
        this.pocitadloZvisle += 1;
    }
    
    public void odpocitajPocitadloVodorovne() {
        this.pocitadloVodorovne -= 1;
    }
    
    public void pripocitajPocitadloVodorovne() {
        this.pocitadloVodorovne += 1;
    }
    
    public void setPocitadloZvisle(int hodnota) {
        this.pocitadloZvisle = hodnota;
    }
    
    public void setPocitadloVodorovne(int hodnota) {
        this.pocitadloVodorovne = hodnota;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public ArrayList<Smery> getHistoriaPohybu() {
        return this.historiaPohybu;
    }
    
    public Smery getPoslednyZvislyPohyb() {
        for (int i = this.historiaPohybu.size() - 1; i >= 0; i--) {
            if (this.historiaPohybu.get(i) == Smery.HORE || this.historiaPohybu.get(i) == Smery.DOLE) {
                return this.historiaPohybu.get(i);
            }
        }
        return null;
    }
    
    public Smery getPoslednyVodorovnyPohyb() {
        for (int i = this.historiaPohybu.size() - 1; i >= 0; i--) {
            if (this.historiaPohybu.get(i) == Smery.VPRAVO || this.historiaPohybu.get(i) == Smery.VLAVO) {
                return this.historiaPohybu.get(i);
            }
        }
        return null;
    }
    
    public void posunHore() {
        this.pavuk.posunZvisle(-this.typPavuka.getRychlostPavuka());
        this.historiaPohybu.add(Smery.HORE);
        this.y += -this.typPavuka.getRychlostPavuka();
    }
    
    public void posunDole() {
        this.pavuk.posunZvisle(this.typPavuka.getRychlostPavuka());
        this.historiaPohybu.add(Smery.DOLE);
        this.y += this.typPavuka.getRychlostPavuka();
    }
    
    public void posunVpravo() {
        this.pavuk.posunVodorovne(this.typPavuka.getRychlostPavuka());
        this.historiaPohybu.add(Smery.VPRAVO);
        this.x += this.typPavuka.getRychlostPavuka();
    }
    
    public void posunVlavo() {
        this.pavuk.posunVodorovne(-this.typPavuka.getRychlostPavuka());
        this.historiaPohybu.add(Smery.VLAVO);
        this.x += -this.typPavuka.getRychlostPavuka();
    }
    
    public void vykresli() {
        this.pavuk.zobraz();
    }
    
    public void skry() {
        this.pavuk.skry();
    }
    
    public void zmenPoziciu(int x, int y) {
        this.x = x;
        this.y = y;
        this.pavuk.zmenPolohu(this.x, this.y);
    }
}