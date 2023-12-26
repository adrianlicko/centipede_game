import fri.shapesge.Obrazok;
import java.util.ArrayList;

public class Centipede {

    private ArrayList<CastiTela> centipede; //Zoznam častí tela
    private ArrayList<Smery> historiaPohybu; //Zoznam všetkých doterajších posunov hlavy, telo hlavu následuje
    private Smery smer;
    private int x;
    private int y;

    public Centipede(int dlzkaTela) {
        this.centipede = new ArrayList<CastiTela>();
        this.historiaPohybu = new ArrayList<Smery>();
        // Súradnice začiatočnej pozícií hlavy
        this.x = 360;
        this.y = -40;

        this.centipede.add(new CastiTela("pics\\centipedeHead.png" ,this.x, this.y)); //Na prvej pozícií musí byť vždy hlava
        for (int i = 0; i < dlzkaTela; i++) {
            this.centipede.add(new CastiTela("pics\\centipedeBody.png", this.x+((i+1)*20), this.y));
            for (int j = 0; j < 10; j++) {
                this.historiaPohybu.add(Smery.VLAVO);
            }
        }
    }

    public int getSurHlavyX() {
        return this.centipede.get(0).getX();
    }

    public int getSurHlavyY() {
        return this.centipede.get(0).getY();
    }

    public ArrayList<Smery> getHistoriaPohybu() {
        return this.historiaPohybu;
    }
    
    public void pridajPohyb(Smery smer) {
        this.historiaPohybu.add(smer);
    }

    public Smery getPoslednaZakruta() {
        for (int i = this.historiaPohybu.size() - 1; i >= 0; i--) {
            if (this.historiaPohybu.get(i) == Smery.VPRAVO || this.historiaPohybu.get(i) == Smery.VLAVO) {
                return this.historiaPohybu.get(i);
            }
        }
        return null;
    }

    public void vykresli() {
        for (int i = 0; i < this.centipede.size(); i++) {
            this.centipede.get(i).zobraz();
        }
    }

    public void posunHore() {
        this.centipede.get(0).posunZvisle(Smery.HORE.getVektor());
        this.pridajPohyb(smer.HORE);
        this.nasledujHlavu();
    }

    public void posunDole() {
        this.centipede.get(0).posunZvisle(Smery.DOLE.getVektor());
        this.pridajPohyb(smer.DOLE);
        this.nasledujHlavu();
    }

    public void posunVpravo() {
        this.centipede.get(0).posunVodorovne(Smery.VPRAVO.getVektor());
        this.pridajPohyb(smer.VPRAVO);
        this.nasledujHlavu();
    }

    public void posunVlavo() {
        this.centipede.get(0).posunVodorovne(Smery.VLAVO.getVektor());
        this.pridajPohyb(smer.VLAVO);
        this.nasledujHlavu();
    }

    public void zmenPoziciuHlavy(int x, int y) {
        this.centipede.get(0).zmenPolohu(x, y);
    }

    public void nasledujHlavu() {
        for (int i = 1; i < this.centipede.size(); i++) {
            Smery pohyb = this.historiaPohybu.get(this.historiaPohybu.size() - (i*10));
            if (pohyb == Smery.HORE || pohyb == Smery.DOLE) {
                this.centipede.get(i).posunZvisle(pohyb.getVektor());
            } else {
                this.centipede.get(i).posunVodorovne(pohyb.getVektor());
            }
        }
    }

    public void printniHistoriuPohybu() {
        for (int i = 0; i < this.historiaPohybu.size(); i++)
            System.out.println(this.historiaPohybu.get(i));
    }

    public void printniPozicie() {
        for (int i = 0; i < this.centipede.size(); i++) {
            System.out.println(this.centipede.get(i).getX() + " " + this.centipede.get(i).getY());
        }
    }
}