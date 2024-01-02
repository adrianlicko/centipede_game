import fri.shapesge.Obdlznik;

public class Naboj {
    private Obdlznik naboj;
    private Raketa raketa;
    private int x, y;

    public Naboj(Raketa r) {
        this.raketa = r;
        this.x = this.raketa.getX();
        this.y = this.raketa.getY();
        
        this.naboj = new Obdlznik(this.x, this.y);
        this.naboj.zmenStrany(3, 10);
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void vykresli() {
        this.naboj.zobraz();
    }
    
    public void skry() {
        this.naboj.skry();
    }

    public void posunHore() {
        this.naboj.posunZvisle(-3);
        this.y += -3;
    }
    
    public void zmenPolohu(int surX, int surY) {
        this.naboj.zmenPolohu(surX, surY);
    }
}