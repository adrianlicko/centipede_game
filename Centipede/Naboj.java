import fri.shapesge.Obdlznik;

/**
 * Predstavuje jeden náboj.
 * 
 * @author Adrián Ličko
 */
public class Naboj {
    private final Obdlznik naboj;
    private final VesmirnaLod lod;
    private int x;
    private int y;
    private final TypLode typLode;

    /**
     * @param l, enum, z ktorého sa berie rýchlosť a farba náboja.
     * @param r, vesmírna loď z ktorej náboj vystrelí.
     */
    public Naboj(TypLode tl, VesmirnaLod l) {
        this.typLode = tl;
        this.lod = l;
        this.x = this.lod.getX();
        this.y = this.lod.getY();
        
        this.naboj = new Obdlznik(this.x, this.y);
        this.naboj.zmenFarbu(this.typLode.getFarbaNaboja());
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
        this.naboj.posunZvisle(-this.typLode.getRychlostNaboja());
        this.y += -this.typLode.getRychlostNaboja();
    }
    
    public void zmenPolohu(int surX, int surY) {
        this.naboj.zmenPolohu(surX, surY);
    }
}