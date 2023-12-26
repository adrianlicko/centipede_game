import java.util.ArrayList;
import java.util.Random;

public class OvladanieCentipede {
    private ArrayList<Centipede> centipede;
    private Smery smer;
    private Prekazky prekazky;
    private Random random;
    private int pocitadloPosunDole;
    private int pocitadloPosunHore;
    private int[] moznyPocetPosunuti;

    public OvladanieCentipede(int dlzka, Prekazky instancia) {
        // Zoznam jednotlivých centipede, každá centipede obsahuje zoznam tela
        this.centipede = new ArrayList<Centipede>();
        this.centipede.add(new Centipede(dlzka));

        // Slúži na to, aby sa centipede posunula o určitý počet krát dole alebo hore a nie iba raz
        this.pocitadloPosunDole = 0;
        this.pocitadloPosunHore = 0;
        // Využíva sa keď je centipede na spodku mapy. Náhodne o jedno z týchto čísel sa posunie hore.
        this.moznyPocetPosunuti = new int[]{9, 19, 29, 39, 49};

        this.prekazky = instancia; // Trieda Hra vytvára inštanciu na prekážky

        this.centipede.get(0).vykresli(); // Na začiatku hry bude vždy iba jedna centipede, rozmnoží sa až keď do nej hráč strelí 

        // 2 posuny dole, pretože jej spawn je nad plátnom
        for (int i = 0; i < 10; i++) {
            this.centipede.get(0).posunDole();
            this.centipede.get(0).posunDole();
        }

        this.random = new Random();
        // Náhodný začiatočný smer
        switch (random.nextInt(2)) {
            case 0:
                this.centipede.get(0).posunVpravo();
                break;
            case 1:
                this.centipede.get(0).posunVlavo();
                break;
        }
    }

    /**
     * Metódu vyvoláva manažér, opakuje sa stále dokola po určitom tiku.
     * Zabezpečuje správny pohyb centipede v prípade ak narazí do prekážky alebo konca mapy.
     */
    public void ovladanieCentipedePocitacom() {
        Smery predosliSmer = this.centipede.get(0).getHistoriaPohybu().get(this.centipede.get(0).getHistoriaPohybu().size() - 1);
        Smery predoslaZakruta = this.centipede.get(0).getPoslednaZakruta();

        int suradnicaHlavyX = this.centipede.get(0).getSurHlavyX();
        int suradnicaHlavyY = this.centipede.get(0).getSurHlavyY();

        switch (predosliSmer) {
            case HORE:
                if (this.pocitadloPosunHore != 0) {
                    this.centipede.get(0).posunHore();
                    this.pocitadloPosunHore--;
                    break;
                }
                
                if (predoslaZakruta == Smery.VPRAVO) {
                    this.centipede.get(0).posunVlavo();
                } else {
                    this.centipede.get(0).posunVpravo();
                }
                break;
            case DOLE:
                if (this.pocitadloPosunDole != 0) {
                    this.centipede.get(0).posunDole();
                    this.pocitadloPosunDole--;
                    break;
                }
                
                if (predoslaZakruta == Smery.VPRAVO) {
                    this.centipede.get(0).posunVlavo();
                } else {
                    this.centipede.get(0).posunVpravo();
                }
                break;
            case VPRAVO:
                if (jeVPrekazke(suradnicaHlavyX + 20, suradnicaHlavyY)) {
                    if (this.centipede.get(0).getSurHlavyY() == 680) {
                        this.centipede.get(0).posunHore();
                        this.pocitadloPosunHore = this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)];
                    } else {
                        this.centipede.get(0).posunDole();
                        this.pocitadloPosunDole = 9;
                    }
                } else {
                    this.centipede.get(0).posunVpravo();
                }
                break;
            case VLAVO:
                if (jeVPrekazke(suradnicaHlavyX, suradnicaHlavyY)) {
                    if (this.centipede.get(0).getSurHlavyY() == 680) {
                        this.centipede.get(0).posunHore();
                        this.pocitadloPosunHore = this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)];
                    } else {
                        this.centipede.get(0).posunDole();
                        this.pocitadloPosunDole = 9;
                    }
                } else {
                    this.centipede.get(0).posunVlavo();
                }
                break;
        }
    }

    /**
     * Slúži iba pre metódu ovladanieCentipedePocitacom(), ktorá ju sama vyvoláva.
     * Vracia návratovú hodnotu boolean na základe toho, či zadané súradnice v parametroch sú zhodné, resp. či sa nachádzajú v súradniciach prekážok alebo či sú mimo mapy.
     */
    private boolean jeVPrekazke(int surX, int surY) {
        for (int i = 0; i < this.prekazky.getVsetkySurX().length; i++) {
            boolean jeMimoMapy = surX <= 0 || surX >= 790;
            if ( ((surX >= this.prekazky.getVsetkySurX()[i] && surX < this.prekazky.getVsetkySurX()[i] + 20) && (surY + 20 > this.prekazky.getVsetkySurY()[i] && surY - 20 < this.prekazky.getVsetkySurY()[i])) || jeMimoMapy ) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList getVsetkyCentipede() {
        return this.centipede;
    }
}