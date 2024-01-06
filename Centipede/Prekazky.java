import java.util.ArrayList;
import java.util.Random;

/**
 * Predstavuje viacero prekážok, kameňov.
 *
 * @author Adrián Ličko
 * 
 * 
 * Constructor for objects of class sd
 * 
 * 
 * An example of a method - replace this comment with your own
 * 
 * @param  y  a sample parameter for a method
 * @return    the sum of x and y
 */
public class Prekazky {
    private ArrayList<Kamen> kamene;
    private final int pocetPrekazok;
    private Random random;

    public Prekazky(int pocetPrekazok) {
        this.kamene = new ArrayList<Kamen>();
        this.pocetPrekazok = pocetPrekazok;
    }

    /**
     * Prekážky sa generujú tak, že sa náhodne vyberú súradnice podľa veľkosti plátna.
     * Následne sa súradnice generujú stále dokola až kým nie sú delitelné číslom 20. Je to z dôvodu aby sa uchoval grid, kvôli pohybu centipede.
     * Potom sa ešte prekontrolujú cez spravneHodnoty či nenastal problém, kedy by centipede narazila do prekážky a rovno pod ňou, resp. na jednom z jej rohu by bola dalšia. 
     * V takom prípade sa prekážke na takom mieste nesmie vygenerovať.
     */
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

            boolean spravneHodnoty = true;
            for (Kamen k : this.kamene) {
                if ((suradnicaX + 20 == k.getX() || suradnicaX - 20 == k.getX()) && (suradnicaY - 20 == k.getY() || suradnicaY + 20 == k.getY())) {
                    spravneHodnoty = false;
                    i--;
                    break;
                }
            }

            if (spravneHodnoty) {
                this.kamene.add(new Kamen(suradnicaX, suradnicaY));
                this.kamene.get(this.kamene.size() - 1).vykresli();
            }
        }
    }

    public ArrayList<Kamen> getKamene() {
        return this.kamene;
    }

    public void setKamene(ArrayList<Kamen> noveKamene) {
        this.kamene = noveKamene;
    }

    public void skry() {
        for (Kamen k : this.kamene) {
            k.skry();
        }
    }
}