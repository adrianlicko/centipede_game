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
    private Menu menu;
    
    public Hra() {
        
    }
    
    public void spustiHru(int dlzkaCentipede, int pocetPrekazok) {
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
        
        this.manazer.spravujObjekt(this);
    }
    
    public void vypniHru() {
        this.manazer.prestanSpravovatObjekt(this);
        this.manazer.prestanSpravovatObjekt(this.ovladanieRaketa);
        this.manazer.prestanSpravovatObjekt(this.ovladaniePavuk);
        this.manazer.prestanSpravovatObjekt(this.ovladanieCentipede);
        this.manazer = null;
        
        this.ovladanieRaketa.skry();
        this.ovladanieRaketa = null;
        
        HUD.getInstancia().setDefaultHodnoty();
        HUD.getInstancia().skry();
        //this.lod = null;
        
        this.ovladaniePavuk.skry();
        this.ovladaniePavuk = null;
        //this.pavuk = null;
        
        this.ovladanieCentipede.skry();
        this.ovladanieCentipede = null;
        
        this.prekazky.skry();
        this.prekazky = null;
        
        this.platno.skry();
        this.platno = null;
        
        
        
        
        this.menu.vypniHru();
    }
}