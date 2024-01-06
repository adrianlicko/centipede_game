import fri.shapesge.Obrazok;

/**
 * Kameň, prekážka pre ostatné objekty
 *
 * @author Adrián Ličko
 */
public class Kamen {
    private final Obrazok kamen;
    private final int x;
    private final int y;

    public Kamen(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.kamen = new Obrazok("pics\\rock.png", this.x, this.y);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void vykresli() {
        this.kamen.zobraz();
    }
    
    public void skry() {
        this.kamen.skry(); 
    }
    
    public void zmenObrazok(String cestaKObrazku) {
        this.kamen.zmenObrazok(cestaKObrazku);
    }
}