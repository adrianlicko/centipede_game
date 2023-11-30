import fri.shapesge.Obrazok;

public class CastiTela {
    private Obrazok castTela;
    private int x;
    private int y;

    public CastiTela(String cestaKObrazku, int x, int y) {
        this.castTela = new Obrazok(cestaKObrazku, x, y);
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void zobraz() {
        this.castTela.zobraz();
    }
    
    public void skry() {
        this.castTela.skry(); 
    }
    
    public void posunVodorovne(int dialka) {
        this.castTela.posunVodorovne(dialka);
        this.x += dialka;
    }
    
    public void posunZvisle(int dialka) {
        this.castTela.posunZvisle(dialka);
        this.y += dialka;
    }
    
    public void zmenUhol(int uhol) {
        this.castTela.zmenUhol(uhol);
    }
    
    public void zmenObrazok(String cestaKObrazku) {
        this.castTela.zmenObrazok(cestaKObrazku);
    }
    
    public void zmenPolohu(int x, int y) {
        this.castTela.zmenPolohu(x, y);
        this.x = x;
        this.y = y;
    }
    
}