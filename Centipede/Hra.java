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
        
        
        this.manazer = new Manazer();
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        //this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        //this.centipede = new Centi(10);
        //this.centipede.vykresli();
        this.manazer.spravujObjekt(this.ovladanieCentipede);
        
    }
    
}