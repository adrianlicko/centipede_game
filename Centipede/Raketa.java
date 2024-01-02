import fri.shapesge.Obrazok;

public class Raketa {
    private Obrazok raketa;
    private int x, y;
    private Naboj naboj;

    public Raketa(int surX, int surY) {
        this.x = surX;
        this.y = surY;
        this.raketa = new Obrazok("pics\\DarkBlueSpaceShip_player.png", this.x, this.y);
        this.raketa.zobraz();
        
        this.naboj = new Naboj(this);
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
        this.raketa.posunZvisle(-6);
        this.y += -6;
    }

    public void posunDole() {
        this.raketa.posunZvisle(6);
        this.y += 6;
    }

    public void posunVpravo() {
        this.raketa.posunVodorovne(6);
        this.x += 6;
    }

    public void posunVlavo() {
        this.raketa.posunVodorovne(-6);
        this.x += -6;
    }
}