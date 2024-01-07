import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Trieda, zodpovedná za ovládanie a kontrolovanie kolízie vystreleného náboja.
 * 
 * @author Adrián Ličko
 * @author Bro Code - časť Timer a TimerTask
 * @author Lakshman Reddy - časť v ovladajNaboje() - iterator
 */
public class OvladanieNaboj {
    private final VesmirnaLod lod;
    private ArrayList<Naboj> naboje;
    private final Prekazky prekazky;
    private final OvladanieCentipede ovladanieCentipede;
    private final TypLode typLode;
    private final OvladaniePavuk ovladaniePavuk;
    private int pocitadlo;
    private final Timer timer;

    /**
     * Vyvoláva trieda Hra.
     * 
     * @param l, vesmírna loď
     * @param p, prekážky
     * @param oc, ovládanie centipede
     * @param tl, typ lode
     * @param op, ovládanie pavúka
     */
    public OvladanieNaboj(VesmirnaLod l, Prekazky p, OvladanieCentipede oc, TypLode tl, OvladaniePavuk op) {
        this.lod = l;
        this.typLode = tl;

        this.naboje = new ArrayList<Naboj>();
        this.prekazky = p;

        this.ovladanieCentipede = oc;

        this.ovladaniePavuk = op;
        
        this.pocitadlo = 0;
        this.timer = new Timer();
    }

    /**
     * Metódu vyvoláva manažér pri stlačení/držaní medzerníka.
     * Spôsobí že raketa vystrelí náboj.
     * Časť kódu, kde sa používa Timer, resp. TimerTask, nie je môj. Poskladal som ho z návodov od Bro Code.
     */
    public void strel() {
        if (this.pocitadlo == 0) {
            this.pocitadlo = 1;
            Naboj n = new Naboj(this.typLode, this.lod);
            this.naboje.add(n);
            n.zmenPolohu(this.lod.getX() + 13, this.lod.getY()); // +13 aby strela vychádzala zo stredu rakety a nie z jej rohu
            n.vykresli();

            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    OvladanieNaboj.this.pocitadlo = 0;
                }
            }, 750); // delay medzi strelami, stlačením medzerníka
        }
    }
    
    /**
     * Metódu vyvoláva manažér stále dokola cez tik.
     * V prípade ak náboj, strela trafí jednu z prekážok alebo vyjde z mapy, tak sa zmaže.
     * Použitie iterátoru z návodov od Lakshman Reddy na vyhnutie sa chyby ConcurrentModificationException počas mazania objektu keď sa s ním iteruje.
     */
    public void ovladajNaboje() {
        Iterator<Naboj> iterator = this.naboje.iterator();
        while (iterator.hasNext()) {
            Naboj n = iterator.next();
            n.posunHore();
            if (this.nabojKoliziaSPrekazkami(n) || this.nabojKoliziaSCentipede(n) || this.nabojMimoMapy(n) || this.nabojKoliziaSPavukom(n)) {
                n.skry();
                iterator.remove();
            }
        }
    }

    /**
     * Slúži pre metódu ovladajNaboje().
     * Kontroluje kolíziu medzi nábojom a prekážkami, kameňmi.
     * @ param n, náboj.
     */
    private boolean nabojKoliziaSPrekazkami(Naboj n) {
        ArrayList<Kamen> kamene = this.prekazky.getKamene();

        for (Kamen k : kamene) {
            if ((n.getX() + 13 >= k.getX()) && (n.getX() < k.getX() + 7) && (n.getY() + 20 > k.getY()) && (n.getY() < k.getY())) {
                k.skry();
                kamene.remove(k);
                this.prekazky.setKamene(kamene);
                HUD.getInstancia().pridajSkore(1);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Slúži pre metódu ovladajNaboje().
     * Kontroluje kolíziu medzi nábojom a centipede.
     * @ param n, náboj.
     */
    private boolean nabojKoliziaSCentipede(Naboj n) {
        ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();

        for (Centipede c : vsetkyCentipede) {
            for (CastiTela cast : c.getTelo()) {
                if ((n.getX() + 13 >= cast.getX()) && (n.getX() < cast.getX() + 12) && (n.getY() + 25 > cast.getY()) && (n.getY() < cast.getY())) {

                    int hitIndex = c.getTelo().indexOf(cast);

                    if (c.getTelo().size() == 1) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        HUD.getInstancia().pridajSkore(10);

                    } else if (c.getTelo().size() != 1 && (hitIndex == c.getTelo().size() - 1)) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        HUD.getInstancia().pridajSkore(5);

                    } else {
                        this.ovladanieCentipede.novaCentipede(c, c.getTelo().size() - hitIndex - 1, cast.getX(), cast.getY());
                        HUD.getInstancia().pridajSkore(5);

                        int pocetCastiNaZmazanie = c.getTelo().size() - hitIndex;
                        for (int i = 0; i < pocetCastiNaZmazanie; i++) {
                            c.getTelo().get(hitIndex).skry();
                            c.getTelo().remove(hitIndex);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Slúži pre metódu ovladajNaboje().
     * Kontroluje kolíziu medzi nábojom a pavúkom.
     * @ param n, náboj.
     */
    private boolean nabojKoliziaSPavukom(Naboj n) {
        for (Pavuk p : this.ovladaniePavuk.getVsetciPavuci()) {
            if ((n.getX() + 11 >= p.getX() && n.getX() < p.getX() + 14) && (n.getY() + 30 > p.getY()) && (n.getY() < p.getY())) {
                p.skry();
                this.ovladaniePavuk.getVsetciPavuci().remove(p);
                HUD.getInstancia().pridajSkore(4);
                this.ovladaniePavuk.novyPavuk();
                return true;
            }
        }
        return false;
    }

    /**
     * Slúži pre metódu ovladajNaboje().
     * Kontroluje či náboj vyšiel z mapy.
     * @ param n, náboj.
     */
    private boolean nabojMimoMapy(Naboj n) {
        return n.getY() <= 0;
    }
    
    public void skry() {
        for (Naboj n : this.naboje) {
            n.skry();
        }
        this.naboje.clear();
        this.naboje = null;
    }
}