import fri.shapesge.Manazer;
import java.util.ArrayList;

public class Hra {
    private Platno platno;
    private Manazer manazer;
    private Centipede centipede;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    
    public Hra(int dlzkaCentipede, int pocetPrekazok) {
        //this.platno = new Platno();
        
        //this.centipede = new Centipede(5);
        //this.centipede.vykresli();
        
        
        //this.manazer = new Manazer();
        //this.manazer.spravujObjekt(this.centipede);
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        
        ovladajCentipede();
    }
    
    
    public void ovladajCentipede() {
        this.ovladanieCentipede.ovladanieCentipedePocitacom();
    }
}