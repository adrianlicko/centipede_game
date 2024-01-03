import fri.shapesge.Manazer;
import java.util.ArrayList;

public class Hra {
    private Platno platno;
    private Manazer manazer;
    private Centipede centipede;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private OvladaniePavuk ovladaniePavuk;
    private OvladanieRaketaANaboj ovladanieRaketa;
    private TypLode lod;
    
    public Hra(int dlzkaCentipede, int pocetPrekazok) {
        this.manazer = new Manazer();
        
        this.platno = new Platno();
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky);
        this.manazer.spravujObjekt(this.ovladanieCentipede);
        
        this.ovladaniePavuk = new OvladaniePavuk(this.prekazky);
        this.manazer.spravujObjekt(this.ovladaniePavuk);
        
        this.lod = TypLode.MODRA;
        HUD.getInstancia().zobrazSkore();
        HUD.getInstancia().zobrazZivoty();
        HUD.getInstancia().pridajZivoty(this.lod.getZivotyRakety());
        
        this.ovladanieRaketa = new OvladanieRaketaANaboj(this.prekazky, this.ovladanieCentipede, this.lod, this.ovladaniePavuk);
        this.manazer.spravujObjekt(this.ovladanieRaketa);
    }
}