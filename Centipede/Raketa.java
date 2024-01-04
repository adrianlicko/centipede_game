import fri.shapesge.Obrazok;

public class Raketa {
    private Obrazok raketa;
    private int x, y;
    private Naboj naboj;
    private TypLode lod;

    public Raketa(TypLode l ,int surX, int surY) {
        this.lod = l;
        this.x = surX;
        this.y = surY;
        this.raketa = new Obrazok(this.lod.getCestaKObrazku(), this.x, this.y);
        this.raketa.zobraz();
        
        this.naboj = new Naboj(this.lod, this);
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void vykresli() {
        this.raketa.zobraz();
    }

    public void posunHore() {
        this.raketa.posunZvisle(-this.lod.getRychlostRakety());
        this.y += -this.lod.getRychlostRakety();
    }

    public void posunDole() {
        this.raketa.posunZvisle(this.lod.getRychlostRakety());
        this.y += this.lod.getRychlostRakety();
    }

    public void posunVpravo() {
        this.raketa.posunVodorovne(this.lod.getRychlostRakety());
        this.x += this.lod.getRychlostRakety();
    }

    public void posunVlavo() {
        this.raketa.posunVodorovne(-this.lod.getRychlostRakety());
        this.x += -this.lod.getRychlostRakety();
    }
    
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
        this.raketa.zmenPolohu(this.x, this.y);
    }
}