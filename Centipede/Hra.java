import fri.shapesge.Manazer;
import java.util.ArrayList;
import java.util.Random;

public class Hra {
    private Platno platno;
    private Manazer manazer;
    private Centipede centipede;
    private ArrayList<Prekazka> kamen;
    private int pocetPrekazok;
    private Random random;
    
    public Hra() {
        //this.platno = new Platno();
        
        this.centipede = new Centipede(5);
        this.centipede.vykresli();
        
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this.centipede);
        
        this.kamen = new ArrayList<Prekazka>();
        this.pocetPrekazok = 40;
    }
    
    public void pridajPrekazky() {
        this.random = new Random();
        
        for (int i = 0; i < this.pocetPrekazok; i++) {
            int suradnicaX;
            int suradnicaY;
            
            for (;;) {
                suradnicaX = random.nextInt(780); //sbge.ini Width - 20
                if (suradnicaX % 20 == 0)
                    break;
            }
            
            for (;;) {
                suradnicaY = random.nextInt(680); //sbge.ini Height - 20
                if (suradnicaY % 20 == 0)
                    break;
            }
            
            this.kamen.add(new Prekazka(suradnicaX, suradnicaY));
        }
    }
}