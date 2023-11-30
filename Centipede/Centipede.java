import fri.shapesge.Obrazok;
import java.util.ArrayList;

public class Centipede {
    public enum Smery {
        ZIADEN,
        HORE,
        DOLE,
        VPRAVO,
        VLAVO
    }
    private ArrayList<CastiTela> centipede;
    public ArrayList<Smery> historiaPohybu;
    private Smery smer;
    private int x;
    private int y;
    
    public Centipede(int dlzkaTela) {
        this.centipede = new ArrayList<CastiTela>();
        this.historiaPohybu = new ArrayList<Smery>();
        this.smer = Smery.ZIADEN;
        this.x = 360;
        this.y = 0;

        this.centipede.add(new CastiTela("pics\\centipedeHead.png" ,this.x, this.y));
        for (int i = 0; i < dlzkaTela; i++) {
            this.centipede.add(new CastiTela("pics\\centipedeBody.png", this.x+((i+1)*20), this.y));
        }
    }

    public void vykresli() {
        for (int i = 0; i < this.centipede.size(); i++) {
            this.centipede.get(i).zobraz();
        }
    }

    
    public void posunHore() {
        nasledujHlavu();
        this.centipede.get(0).posunZvisle(-20);
        this.historiaPohybu.add(smer.HORE);
    }

    public void posunDole() {
        nasledujHlavu();
        this.centipede.get(0).posunZvisle(20);
        this.historiaPohybu.add(smer.DOLE);
    }

    public void posunVpravo() {
        nasledujHlavu();
        this.centipede.get(0).posunVodorovne(20);
        this.historiaPohybu.add(smer.VPRAVO);
    }

    public void posunVlavo() {
        nasledujHlavu();
        this.centipede.get(0).posunVodorovne(-20);
        this.historiaPohybu.add(smer.VLAVO);
    }
    
    public void nasledujHlavu() {
        for (int i = this.centipede.size()-1; i > 0; i--) {
            this.centipede.get(i).zmenPolohu(this.centipede.get(i-1).getX(), this.centipede.get(i-1).getY());
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