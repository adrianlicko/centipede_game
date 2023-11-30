import fri.shapesge.Manazer;
import java.util.ArrayList;

public class Hra {
    private Platno platno;
    private Centipede centipede;
    private Manazer manazer;
    
    public Hra() {
        //this.platno = new Platno();
        
        this.centipede = new Centipede(5);
        this.centipede.vykresli();
        
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this.centipede);
    }
}