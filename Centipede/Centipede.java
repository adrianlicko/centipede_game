import java.util.ArrayList;


/**
 * Jedna celá centipede tvorená z jednotlivých častí.
 *
 * @author Adrián Ličko
 */
public class Centipede {
    private ArrayList<CastiTela> centipede;
    private ArrayList<Smery> historiaPohybu;
    private final Smery smer;
    private final int dlzkaTela;
    private int x;
    private int y;
    private int pocitadlo;
    
    /**
     * @param smer, nastaví začiatočný smer ktorým sa bude centipede uberať.
     * @param dlzkaTela
     * @param surX
     * @param surY
     */
    public Centipede(Smery smer, int dlzkaTela, int surX, int surY) {
        // Zoznam častí tela
        this.centipede = new ArrayList<CastiTela>();
        // Zoznam všetkých doterajších posunov hlavy. Telo hlavu následuje
        this.historiaPohybu = new ArrayList<Smery>();
        this.smer = smer;
        this.dlzkaTela = dlzkaTela;
        // Súradnice začiatočnej pozícií hlavy
        this.x = surX;
        this.y = surY;
        // K počítadlu pristupuje trieda OvladanieCentipede, ktorá mení jeho hodnotu a slúži na udanie počtu posunutí, resp. koľkokrát sa má centipede kam pohnúť
        this.pocitadlo = 0;
        
        this.vytvorCentipede();
    }
    
    /**
     * Vytvorí novú cetipede.
     * Jedna centipede sa skladá z arraylistu, ktorý obsahuje CastiTela.
     * Vždy za prvý prvok sa nahrá hlava, a potom telo.
     */
    public void vytvorCentipede() {
        this.centipede.add(new CastiTela("pics\\centipedeHead.png" , this.x, this.y));
        if (this.smer == Smery.VLAVO) {
            for (int i = 1; i < this.dlzkaTela; i++) {
                this.centipede.add(new CastiTela("pics\\centipedeBody.png", this.x + (i * 20), this.y));
                for (int j = 0; j < 10; j++) {
                    this.historiaPohybu.add(Smery.VLAVO);
                }
            }
        } else {
            for (int i = 1; i < this.dlzkaTela; i++) {
                this.centipede.add(new CastiTela("pics\\centipedeBody.png", this.x + (i * (-20)), this.y));
                for (int j = 0; j < 10; j++) {
                    this.historiaPohybu.add(Smery.VPRAVO);
                }
            }
        }
    }
    
    public int getPocitadlo() {
        return this.pocitadlo;
    }
    
    public void odpocitajZPocitadla() {
        this.pocitadlo -= 1;
    }
    
    public void setPocitadlo(int hodnota) {
        this.pocitadlo = hodnota;
    }

    public int getSurHlavyX() {
        return this.centipede.get(0).getX();
    }

    public int getSurHlavyY() {
        return this.centipede.get(0).getY();
    }

    public Smery getPoslednaZakruta() {
        for (int i = this.historiaPohybu.size() - 1; i >= 0; i--) {
            if (this.historiaPohybu.get(i) == Smery.VPRAVO || this.historiaPohybu.get(i) == Smery.VLAVO) {
                return this.historiaPohybu.get(i);
            }
        }
        return null;
    }

    public void posunHore() {
        this.centipede.get(0).posunZvisle(Smery.HORE.getVektor());
        this.historiaPohybu.add(Smery.HORE);
        this.nasledujHlavu();
    }

    public void posunDole() {
        this.centipede.get(0).posunZvisle(Smery.DOLE.getVektor());
        this.historiaPohybu.add(Smery.DOLE);
        this.nasledujHlavu();
    }

    public void posunVpravo() {
        this.centipede.get(0).posunVodorovne(Smery.VPRAVO.getVektor());
        this.historiaPohybu.add(Smery.VPRAVO);
        this.nasledujHlavu();
    }

    public void posunVlavo() {
        this.centipede.get(0).posunVodorovne(Smery.VLAVO.getVektor());
        this.historiaPohybu.add(Smery.VLAVO);
        this.nasledujHlavu();
    }

    /**
     * Vždy pri každom posune hlavy, sa do histórie pohybu zadá údaj smeru, podľa ktorého sa hýbu ostatné časti centipede.
     */
    public void nasledujHlavu() {
        for (int i = 1; i < this.centipede.size(); i++) {
            Smery pohyb = this.historiaPohybu.get(this.historiaPohybu.size() - (i * 10));
            if (pohyb == Smery.HORE || pohyb == Smery.DOLE) {
                this.centipede.get(i).posunZvisle(pohyb.getVektor());
            } else {
                this.centipede.get(i).posunVodorovne(pohyb.getVektor());
            }
        }
    }
    
    public ArrayList<Smery> getHistoriaPohybu() {
        return this.historiaPohybu;
    }
    
    public ArrayList<CastiTela> getTelo() {
        return this.centipede;
    }
    
    public void vykresli() {
        for (int i = 0; i < this.centipede.size(); i++) {
            this.centipede.get(i).vykresli();
        }
    }
    
    public void skry() {
        for (CastiTela cast : this.centipede) {
            cast.skry();
        }
        this.centipede.clear();
        this.centipede = null;
    }
}