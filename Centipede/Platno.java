import fri.shapesge.Obrazok;
import fri.shapesge.Obdlznik;

public class Platno {
    private Obrazok pozadie;
    private Obdlznik obdlznik;

    public Platno() {
        //this.pozadie = new Obrazok("pics\\plain_black.jpg");
        this.obdlznik = new Obdlznik();
        this.vykresliPozadie();
    }
    
    private void vykresliPozadie() {
        //this.pozadie.posunVodorovne(-100);
        //this.pozadie.posunZvisle(-100);
        //this.pozadie.zobraz();
        
        this.obdlznik.zmenPolohu(0, 700);
        this.obdlznik.zmenFarbu("black");
        this.obdlznik.zmenStrany(800, 30);
        this.obdlznik.zobraz();
    }
}