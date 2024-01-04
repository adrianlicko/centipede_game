import fri.shapesge.Manazer;
import java.util.ArrayList;

public class Hra {
    private Platno platno;
    private Manazer manazer;
    private Centipede centipede;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private OvladaniePavuk ovladaniePavuk;
    private OvladanieRaketa ovladanieRaketa;
    private TypLode lod;
    private TypPavuka pavuk;
    
    public Hra(int dlzkaCentipede, int pocetPrekazok) {
        this.manazer = new Manazer();
        
        this.platno = new Platno();
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        this.manazer.spravujObjekt(this.ovladanieCentipede);
        
        this.pavuk = TypPavuka.CERVENA;
        this.ovladaniePavuk = new OvladaniePavuk(this.prekazky, this.pavuk);
        this.manazer.spravujObjekt(this.ovladaniePavuk);
        
        this.lod = TypLode.CERVENA;
        HUD.getInstancia().zobrazSkore();
        HUD.getInstancia().zobrazZivoty();
        HUD.getInstancia().pridajZivoty(this.lod.getZivotyRakety());
        
        this.ovladanieRaketa = new OvladanieRaketa(this.manazer, this.prekazky, this.ovladanieCentipede, this.lod, this.ovladaniePavuk);
        this.manazer.spravujObjekt(this.ovladanieRaketa);
    }
    
    public void vypniHru() {
        
    }
}