import fri.shapesge.Manazer;
import java.util.ArrayList;

public class Hra {
    private Platno platno;
    private Manazer manazer;
    private Centipede centipede;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private OvladanieRaketaANaboj ovladanieRaketa;
    
    public Hra(int dlzkaCentipede, int pocetPrekazok) {
        this.manazer = new Manazer();
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        this.manazer.spravujObjekt(this.ovladanieCentipede);
        
        this.ovladanieRaketa = new OvladanieRaketaANaboj(this.prekazky, this.ovladanieCentipede);
        this.manazer.spravujObjekt(this.ovladanieRaketa);
    }
}