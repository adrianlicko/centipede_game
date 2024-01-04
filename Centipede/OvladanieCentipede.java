import java.util.ArrayList;
import java.util.Random;

public class OvladanieCentipede {
    private ArrayList<Centipede> centipede;
    private Smery smer;
    private Prekazky prekazky;
    private Random random;
    //private int pocitadlo;
    private int[] moznyPocetPosunuti;

    public OvladanieCentipede(int dlzka, Prekazky p) {
        // Zoznam jednotlivých centipede, každá centipede obsahuje zoznam tela
        this.centipede = new ArrayList<Centipede>();
        
        // Prvá centipede bude vždy na začiatku nad plátnom smerovať dolava
        this.centipede.add(new Centipede(Smery.VLAVO, dlzka, 360, -40));
        
        this.centipede.get(0).vykresli();

        // Slúži na to, aby sa centipede posunula o určitý počet krát, 1 pohyb z histórie pohybu sa vykoná viac krát.
        //this.pocitadlo = 0;
        
        // Využíva sa keď je centipede na spodku mapy. Náhodne o jedno z týchto čísel sa posunie hore.
        this.moznyPocetPosunuti = new int[]{9, 19, 29, 39, 49};

        this.prekazky = p; // Trieda Hra vytvára inštanciu na prekážky

        // 2 posuny dole, pretože jej spawn je nad plátnom
        for (int i = 0; i < 10; i++) {
            this.centipede.get(0).posunDole();
            this.centipede.get(0).posunDole();
        }

        this.random = new Random();
        // Náhodný smer po posunutí dole už keď sa centipede nachádza v hracej ploche
        switch (random.nextInt(2)) {
            case 0:
                this.centipede.get(0).posunVpravo();
                break;
            case 1:
                this.centipede.get(0).posunVlavo();
                break;
        }
    }

    public void novaCentipede(Centipede staraCentipede, int dlzka, int surX, int surY) {
        Smery poslednaZakruta = staraCentipede.getPoslednaZakruta();
        
        this.centipede.add(new Centipede(poslednaZakruta, dlzka, surX, surY));
        this.centipede.get(this.centipede.size() - 1).vykresli();
        this.centipede.get(this.centipede.size() - 1).posunDole();
        this.centipede.get(this.centipede.size() - 1).setPocitadlo(8);
    }

    /**
     * Metódu vyvoláva manažér, opakuje sa stále dokola po určitom tiku.
     * Zabezpečuje správny pohyb centipede v prípade ak narazí do prekážky alebo konca mapy.
     */
    public void ovladanieCentipedePocitacom() {
        for (int i = 0; i < this.centipede.size(); i++) {
            if (this.centipede.get(i).getTelo().size() == 0) {
                this.centipede.remove(i);
                continue;
            }
            
            Smery predosliSmer = this.centipede.get(i).getHistoriaPohybu().get(this.centipede.get(i).getHistoriaPohybu().size() - 1);
            Smery predoslaZakruta = this.centipede.get(i).getPoslednaZakruta();

            int suradnicaHlavyX = this.centipede.get(i).getSurHlavyX();
            int suradnicaHlavyY = this.centipede.get(i).getSurHlavyY();

            switch (predosliSmer) {
                case HORE:
                    if (this.centipede.get(i).getPocitadlo() != 0) {
                        this.centipede.get(i).posunHore();
                        this.centipede.get(i).odpocitajZPocitadla();
                        break;
                    }

                    if (predoslaZakruta == Smery.VPRAVO) {
                        this.centipede.get(i).posunVlavo();
                    } else {
                        this.centipede.get(i).posunVpravo();
                    }
                    break;
                case DOLE:
                    if (this.centipede.get(i).getPocitadlo() != 0) {
                        this.centipede.get(i).posunDole();
                        this.centipede.get(i).odpocitajZPocitadla();
                        break;
                    }

                    if (predoslaZakruta == Smery.VPRAVO) {
                        this.centipede.get(i).posunVlavo();
                    } else {
                        this.centipede.get(i).posunVpravo();
                    }
                    break;
                case VPRAVO:
                    if (jeVPrekazke(suradnicaHlavyX + 20, suradnicaHlavyY)) {
                        if (this.centipede.get(i).getSurHlavyY() == 680) {
                            this.centipede.get(i).posunHore();
                            this.centipede.get(i).setPocitadlo(this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)]);
                        } else {
                            this.centipede.get(i).posunDole();
                            this.centipede.get(i).setPocitadlo(9);
                        }
                    } else {
                        this.centipede.get(i).posunVpravo();
                    }
                    break;
                case VLAVO:
                    if (jeVPrekazke(suradnicaHlavyX, suradnicaHlavyY)) {
                        if (this.centipede.get(i).getSurHlavyY() == 680) {
                            this.centipede.get(i).posunHore();
                            this.centipede.get(i).setPocitadlo(this.moznyPocetPosunuti[this.random.nextInt(this.moznyPocetPosunuti.length - 1)]);
                        } else {
                            this.centipede.get(i).posunDole();
                            this.centipede.get(i).setPocitadlo(9);
                        }
                    } else {
                        this.centipede.get(i).posunVlavo();
                    }
                    break;
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
            boolean jeMimoMapy = surX <= 0 || surX >= 700;
            
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