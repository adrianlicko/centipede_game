import java.util.ArrayList;
import java.util.Random;

public class Prekazky {
    public ArrayList<Kamen> kamene;
    private int pocetPrekazok;
    private Random random;
    private ArrayList<Integer> vsetkySurX;
    private ArrayList<Integer> vsetkySurY;
    
    public Prekazky(int pocetPrekazok) {
        this.kamene = new ArrayList<Kamen>();
        this.pocetPrekazok = pocetPrekazok;
        this.vsetkySurX = new ArrayList<Integer>();
        this.vsetkySurY = new ArrayList<Integer>();
    }
    
    public void pridajKamene() {
        this.random = new Random();
        
        for (int i = 0; i < this.pocetPrekazok; i++) {
            int suradnicaX;
            int suradnicaY;
            
            for (;;) {
                suradnicaX = random.nextInt(741) + 20; //sbge.ini Width -60 +20, zaistenie voľných "políčok" na ľavej a pravej strane mapy
                if (suradnicaX % 20 == 0)
                    break;
            }
            
            for (;;) {
                suradnicaY = random.nextInt(581) + 20; //sbge.ini Height -120 +20, zabránenie generácií prekážok v prvom hornom riadku a dolnej časti mapy pre pohyb raketky
                if (suradnicaY % 20 == 0)
                    break;
            }
            
            /*
             * Problém kedy centipede narazí do kameňa a posunie sa nižšie do dalšieho kameňa.
             * Aby tento problém nenastal, tak riešenie je zabrániť generácií týchto pozíc,
             * a to tak, že na rohových svetových stranách prekážky už nemôže vzniknúť
             * žiadna nová.
             */
            boolean spravneHodnoty = true;
            for (Kamen k : this.kamene) {
                if ( (suradnicaX + 20 == k.getX() || suradnicaX - 20 == k.getX() ) && ( suradnicaY - 20 == k.getY() || suradnicaY + 20 == k.getY()) ) {
                    spravneHodnoty = false;
                    //break;
                }
            }
            
            if (spravneHodnoty == false) {
                i--;
            } else {
                this.kamene.add(new Kamen(suradnicaX, suradnicaY));
                //this.vsetkySurX.add(suradnicaX);
                //this.vsetkySurY.add(suradnicaY);
            }
        }
    }
    
    public ArrayList<Kamen> getKamene() {
        return this.kamene;
    }
    
    public void setKamene(ArrayList noveKamene) {
        this.kamene = noveKamene;
    }
    
    /*
    public ArrayList getVsetkySurX() {
        return vsetkySurX;
    }
    
    public ArrayList getVsetkySurY() {
        return vsetkySurY;
    }
    */
}