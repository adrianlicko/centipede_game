import fri.shapesge.Obrazok;

public class Prekazka {
    private Obrazok kamen;
    private int x;
    private int y;

    public Prekazka(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.kamen = new Obrazok("pics\\rock.png", this.x, this.y);
        this.kamen.zobraz();
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void zobraz() {
        this.kamen.zobraz();
    }
    
    public void skry() {
        this.kamen.skry(); 
    }
    
    public void zmenObrazok(String cestaKObrazku) {
        this.kamen.zmenObrazok(cestaKObrazku);
    }
}