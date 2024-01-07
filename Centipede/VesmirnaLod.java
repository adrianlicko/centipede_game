import fri.shapesge.Obrazok;

/**
 * Vesmírna loď, ktorú ovláda hráč.
 * 
 * @author Adrián Ličko
 */
public class VesmirnaLod {
    private final Obrazok lod;
    private int x;
    private int y;
    private final Naboj naboj;
    private final TypLode typLode;

    /**
     * @param TypLode tl, dostáva enum typu lode.
     * @param surX
     * @param surY
     */
    public VesmirnaLod(TypLode tl, int surX, int surY) {
        this.typLode = tl;
        this.x = surX;
        this.y = surY;
        this.lod = new Obrazok(this.typLode.getCestaKObrazku(), this.x, this.y);
        this.lod.zobraz();
        
        this.naboj = new Naboj(this.typLode, this);
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void vykresli() {
        this.lod.zobraz();
    }
    
    public void skry() {
        this.lod.skry();
    }

    public void posunHore() {
        this.lod.posunZvisle(-this.typLode.getRychlostLode());
        this.y += -this.typLode.getRychlostLode();
    }

    public void posunDole() {
        this.lod.posunZvisle(this.typLode.getRychlostLode());
        this.y += this.typLode.getRychlostLode();
    }

    public void posunVpravo() {
        this.lod.posunVodorovne(this.typLode.getRychlostLode());
        this.x += this.typLode.getRychlostLode();
    }

    public void posunVlavo() {
        this.lod.posunVodorovne(-this.typLode.getRychlostLode());
        this.x += -this.typLode.getRychlostLode();
    }
    
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
        this.lod.zmenPolohu(this.x, this.y);
    }
}