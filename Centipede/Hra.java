import fri.shapesge.Manazer;
import java.util.ArrayList;

/**
 * Vytvára celú hru. Spája všetky objekty dokopy.
 * 
 * @author Adrián Ličko
 */
public class Hra {
    private Manazer manazer;
    private Centipede centipede;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private OvladaniePavuk ovladaniePavuk;
    private OvladanieVesmirnaLod ovladanieLod;
    private TypLode typLode;
    private TypPavuka typPavuka;
    private Menu menu;
    private Obchod obchod;
    
    /**
     * @param m, inštancia menu, z dốvodu možnosti ukončenia hry.
     * @param o, inštancia obchodu, aby KeyListener nereagoval na obchod keď je hra spustená.
     */
    public Hra(Menu m, Obchod o) {
        this.menu = m;
        this.obchod = o;
    }
    
    /**
     * Hlavná metóda na spustenie hry.
     * @param dlzkaCentipede, dlžka na začiatku hry
     * @param pocetPrekazok, počet prekážok na začiatku hry
     */
    public void spustiHru(int dlzkaCentipede, int pocetPrekazok) {
        this.manazer = new Manazer();
        
        this.prekazky = new Prekazky(pocetPrekazok);
        this.prekazky.pridajKamene();
        
        this.ovladanieCentipede = new OvladanieCentipede(dlzkaCentipede, this.prekazky, this);
        this.manazer.spravujObjekt(this.ovladanieCentipede);
        
        if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.MODRA)) {
            this.typLode = TypLode.MODRA;
            this.typPavuka = TypPavuka.MODRA;
        } else if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.CERVENA)) {
            this.typLode = TypLode.CERVENA;
            this.typPavuka = TypPavuka.CERVENA;
        } else if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.ZLTA)) {
            this.typLode = TypLode.ZLTA;
            this.typPavuka = TypPavuka.ZLTA;
        } else {
            System.out.println("Zla farba");
        }
        
        this.ovladaniePavuk = new OvladaniePavuk(this.typPavuka);
        this.manazer.spravujObjekt(this.ovladaniePavuk);
        
        HUD.getInstancia().zobrazSkore();
        HUD.getInstancia().zobrazZivoty();
        HUD.getInstancia().pridajZivoty(this.typLode.getZivotyLode());
        
        this.ovladanieLod = new OvladanieVesmirnaLod(this.manazer, this.prekazky, this.ovladanieCentipede, this.typLode, this.ovladaniePavuk, this);
        this.manazer.spravujObjekt(this.ovladanieLod);
        
        this.manazer.spravujObjekt(this);
    }
    
    /**
     * Metóda ktorú vyvoláva manažér cez KeyListener na klávesu Escape.
     * Ak hráč ukonči takto hru, čiže nezničí všetky centipede na hracej ploche, tak prehral, čiže sa mu nazbierané skóre nepripočíta.
     */
    public void escUkoncenie() {
        if (this.obchod == null) {
            this.vypniHru("prehra");
        }
    }
    
    /**
     * Ukončuje hru, skrýva zobrazené objekty a posiela správu vypni hru menu.
     * Ak sa hra skončí výhrou, tak sa skóre pripočíta ku skóre ktoré už má.
     * @param stav, indikátor výhry alebo prehry typu String
     */
    public void vypniHru(String stav) {
        this.manazer.prestanSpravovatObjekt(this);
        this.manazer.prestanSpravovatObjekt(this.ovladanieLod);
        this.manazer.prestanSpravovatObjekt(this.ovladaniePavuk);
        this.manazer.prestanSpravovatObjekt(this.ovladanieCentipede);
        this.manazer = null;
        
        this.ovladanieLod.skry();
        this.ovladanieLod = null;
        
        if (stav.equals("vyhra")) {
            UdajeZoSuboru.getInstancia().zapisSkore(HUD.getInstancia().getSkore());
            UdajeZoSuboru.getInstancia().pripocitajLevel();
        }
        HUD.getInstancia().setDefaultHodnoty();
        HUD.getInstancia().skry();
        
        this.ovladaniePavuk.skry();
        this.ovladaniePavuk = null;
        
        this.ovladanieCentipede.skry();
        this.ovladanieCentipede = null;
        
        this.prekazky.skry();
        this.prekazky = null;
        
        
        this.menu.vypniHruCentipede(stav);
    }
    
    
}