import java.util.ArrayList;
import java.util.Random;

public class Prekazky {
    public ArrayList<Kamen> kamen;
    private int pocetPrekazok;
    private Random random;
    
    public Prekazky(int pocetPrekazok) {
        this.kamen = new ArrayList<Kamen>();
        this.pocetPrekazok = pocetPrekazok;
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
            for (int j = 0; j < this.kamen.size(); j++) {
                if ( (suradnicaX + 20 == this.kamen.get(j).getX() || suradnicaX - 20 == this.kamen.get(j).getX() ) && ( suradnicaY - 20 == this.kamen.get(j).getY() || suradnicaY + 20 == this.kamen.get(j).getY()) ) {
                    spravneHodnoty = false;
                    //break;
                }
            }
            
            if (spravneHodnoty == false) {
                i--;
            } else
                this.kamen.add(new Kamen(suradnicaX, suradnicaY));
        }
    }
    
    public int[] getVsetkySurX() {
        int[] vsetkySuradnice = new int[this.kamen.size()];
        for (int i = 0; i < this.kamen.size(); i++) {
            vsetkySuradnice[i] = this.kamen.get(i).getX();
        }
        return vsetkySuradnice;
    }
    
    public int[] getVsetkySurY() {
        int[] vsetkySuradnice = new int[this.kamen.size()];
        for (int i = 0; i < this.kamen.size(); i++) {
            vsetkySuradnice[i] = this.kamen.get(i).getY();
        }
        return vsetkySuradnice;
    }
}