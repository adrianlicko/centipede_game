import fri.shapesge.Obdlznik;

public class Naboj {
    private Obdlznik naboj;
    private Raketa raketa;
    private int x, y;
    private TypLode lod;

    public Naboj(TypLode l, Raketa r) {
        this.lod = l;
        this.raketa = r;
        this.x = this.raketa.getX();
        this.y = this.raketa.getY();
        
        this.naboj = new Obdlznik(this.x, this.y);
        this.naboj.zmenFarbu(this.lod.getFarbaNaboja());
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
        this.naboj.posunZvisle(-this.lod.getRychlostNaboja());
        this.y += -this.lod.getRychlostNaboja();
    }
    
    public void zmenPolohu(int surX, int surY) {
        this.naboj.zmenPolohu(surX, surY);
    }
}