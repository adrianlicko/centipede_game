import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda, zodpovedná za ovládanie všetkých vytvorených centipede.
 * 
 * @author Adrián Ličko
 */
public class OvladanieCentipede {
    private ArrayList<Centipede> centipede;
    private Smery smer;
    private final Prekazky prekazky;
    private Random random;
    private final int[] moznyPocetPosunuti;
    private final Hra hra;
    
    /**
     * Inštanciu vyvoláva trieda Hra
     * 
     * @param dlzka, dĺžka prvej centipede na začiatku hry.
     * @param p, získanie prekážok, objektov od ktorých sa centipede odrazí.
     * @param h, získanie inštancie, z dôvodu, aby hru vedeli ukončiť.
     */
    public OvladanieCentipede(int dlzka, Prekazky p, Hra h) {
        // Zoznam jednotlivých centipede, každý prvok obsahuje ArrayList, kde sú uložené časti tela
        this.centipede = new ArrayList<Centipede>();
        // Vytvorenie prvej centipede na začiatku hry
        this.centipede.add(new Centipede(Smery.VLAVO, dlzka, 360, -40));
        this.centipede.get(0).vykresli();
        /*
         * Využíva sa, keď je centipede úplne dole na mape a posunom dole by už vyšla z mapy
         * Vtedy sa posunie hore o náhodne zvolený prvok z poľa
         */
        this.moznyPocetPosunuti = new int[]{29, 39, 49, 59, 69};
        this.hra = h;
        this.prekazky = p;

        // spawn prvej centipede je nad plátnom, takže ju musíme posunúť dole
        for (int i = 0; i < 10; i++) {
            this.centipede.get(0).posunDole();
            this.centipede.get(0).posunDole();
        }

        this.random = new Random();
        // Náhodne zvolený smer
        switch (this.random.nextInt(2)) {
            case 0:
                this.centipede.get(0).posunVpravo();
                break;
            case 1:
                this.centipede.get(0).posunVlavo();
                break;
        }
    }
    
    /**
     * Vytvára novú centipede podľa zadaných parametrov.
     * Metódu vyvoláva trieda OvladanieNaboj.
     * 
     * @param staraCentipede, predchodca z ktorej nová centipede vznikla
     * @param dlzka, dĺžka novej centipede
     * @param surX
     * @param surY
     */
    public void novaCentipede(Centipede staraCentipede, int dlzka, int surX, int surY) {
        Smery poslednaZakruta = staraCentipede.getPoslednaZakruta();

        this.centipede.add(new Centipede(poslednaZakruta, dlzka, surX, surY));
        this.centipede.get(this.centipede.size() - 1).vykresli();
        this.centipede.get(this.centipede.size() - 1).posunDole();
        this.centipede.get(this.centipede.size() - 1).setPocitadlo(8);
    }

    /**
     * Metódu vyvoláva manažér, opakuje sa stále dokola po určitom tiku.
     * Zabezpečuje správny pohyb centipede, v prípade, ak narazí do prekážky alebo konca mapy.
     */
    public void ovladanieCentipedePocitacom() {
        if (this.centipede.size() == 0) {
            this.hra.vypniHru("vyhra"); // ukončenie hry, v prípade, ak sa už žiadna centipede nenachádza
        }

        if (this.centipede != null) {
            for (int i = 0; i < this.centipede.size(); i++) {
                Centipede c = this.centipede.get(i);
                
                if (c.getTelo().size() == 0) {
                    this.centipede.remove(i);
                    continue;
                }

                Smery predosliSmer = c.getHistoriaPohybu().get(c.getHistoriaPohybu().size() - 1);
                Smery predoslaZakruta = c.getPoslednaZakruta();

                int suradnicaHlavyX = c.getSurHlavyX();
                int suradnicaHlavyY = c.getSurHlavyY();

                switch (predosliSmer) {
                    case HORE:
                        if (c.getPocitadlo() != 0) {
                            c.posunHore();
                            c.odpocitajZPocitadla();
                            break;
                        }

                        if (predoslaZakruta == Smery.VPRAVO) {
                            c.posunVlavo();
                        } else {
                            c.posunVpravo();
                        }
                        break;
                    case DOLE:
                        if (c.getPocitadlo() != 0) {
                            c.posunDole();
                            c.odpocitajZPocitadla();
                            break;
                        }

                        if (predoslaZakruta == Smery.VPRAVO) {
                            c.posunVlavo();
                        } else {
                            c.posunVpravo();
                        }
                        break;
                    case VPRAVO:
                        if (this.jeVPrekazke(suradnicaHlavyX + 20, suradnicaHlavyY)) {
                            if (c.getSurHlavyY() >= 665) {
                                c.posunHore();
                                c.setPocitadlo(this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)]);
                            } else {
                                c.posunDole();
                                c.setPocitadlo(9);
                            }
                        } else {
                            c.posunVpravo();
                        }
                        break;
                    case VLAVO:
                        if (this.jeVPrekazke(suradnicaHlavyX, suradnicaHlavyY)) {
                            if (c.getSurHlavyY() >= 665) {
                                c.posunHore();
                                c.setPocitadlo(this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)]);
                            } else {
                                c.posunDole();
                                c.setPocitadlo(9);
                            }
                        } else {
                            c.posunVlavo();
                        }
                        break;
                }
            }
        }
    }

    /**
     * Slúži iba pre metódu ovladanieCentipedePocitacom(), ktorá ju sama vyvoláva.
     * Vracia návratovú hodnotu boolean na základe toho, či zadané súradnice v parametroch sú zhodné, resp. či sa nachádzajú v súradniciach prekážok alebo či sú mimo mapy.
     */
    private boolean jeVPrekazke(int surX, int surY) {
        ArrayList<Kamen> kamene = this.prekazky.getKamene();

        for (Kamen k : kamene) {
            boolean jeMimoMapy = surX <= 0 || surX >= 800;

            if ( ((surX >= k.getX() && surX < k.getX() + 20) && (surY + 20 > k.getY() && surY - 20 < k.getY())) || jeMimoMapy ) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Centipede> getVsetkyCentipede() {
        return this.centipede;
    }

    public void skry() {
        for (Centipede c : this.centipede) {
            c.skry();
        }
        this.centipede.clear();
        this.centipede = null;
    }
}