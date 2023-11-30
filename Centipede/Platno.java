import fri.shapesge.Obrazok;

public class Platno {
    private Obrazok pozadie;

    public Platno() {
        this.pozadie = new Obrazok("pics\\plain_black.jpg");
        vykresliPozadie();
    }
    
    private void vykresliPozadie() {
        this.pozadie.posunVodorovne(-100);
        this.pozadie.posunZvisle(-100);
        this.pozadie.zobraz();
    }
}