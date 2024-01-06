import fri.shapesge.Manazer;
import java.util.ArrayList;

/**
 * Trieda, ktorá je zodpovedná za ovládanie vesmírnej lodi.
 * 
 * @author Adrián Ličko
 */
public class OvladanieVesmirnaLod {
    private final VesmirnaLod lod;
    private final TypLode typLode;
    private final Prekazky prekazky;
    private final OvladanieCentipede ovladanieCentipede;
    private final OvladaniePavuk ovladaniePavuk;
    private OvladanieNaboj ovladanieNaboj;
    private final Manazer manazer;
    private final Hra hra;

    /**
     * @param m, manžér pre triedu ovládania nábojov.
     * @param p, prekážky.
     * @param oc, ovládanie centipede.
     * @param tl, typy vesmírnych lodí.
     * @param op, ovládanie pavúka.
     * @param h, inštancia na triedu Hra z dôvodu ukončenia.
     */
    public OvladanieVesmirnaLod(Manazer m, Prekazky p, OvladanieCentipede oc, TypLode tl, OvladaniePavuk op, Hra h) {
        this.typLode = tl;
        this.lod = new VesmirnaLod(this.typLode, 370, 670);
        this.lod.vykresli();
        this.prekazky = p;
        this.ovladanieCentipede = oc;
        this.ovladaniePavuk = op;
        this.ovladanieNaboj = new OvladanieNaboj(this.lod, this.prekazky, this.ovladanieCentipede, this.typLode, this.ovladaniePavuk);
        this.manazer = m;
        this.manazer.spravujObjekt(this.ovladanieNaboj);
        this.hra = h;
    }

    /**
     * Metóda vyvolávaná manžérom pomocou tiku.
     * Kontroluje všetky možné kolízie, ktoré môžu s loďou nastať.
     * V prípade minutia životov sa hra ukončí.
     */
    public void kontrolujKolizieSLodou() {
        if (this.lodKoliziaSCentipede() || this.lodKoliziaSPavukom() || this.jeMimoMapy()) {
            this.lod.zmenPolohu(370, 670);
            HUD.getInstancia().uberZivoty();
            if (HUD.getInstancia().getPocetZivotov() == 0) {
                this.hra.vypniHru("prehra");
            }
        }
    }
    
    /**
     * Vyvolávané metódou na kontrolu kolízie s loďou.
     * Kontroluje či sa loď stretla s pavúkom.
     */
    private boolean lodKoliziaSPavukom() {
        for (Pavuk p : this.ovladaniePavuk.getVsetkyPavuci()) {
            if ((this.lod.getX() + 30 > p.getX()) && (this.lod.getX() < p.getX() + 25) && (this.lod.getY() + 30 > p.getY()) && (this.lod.getY() < p.getY() + 25)) {
                p.skry();
                this.ovladaniePavuk.getVsetkyPavuci().remove(p);
                this.ovladaniePavuk.novyPavuk();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Vyvolávané metódou na kontrolu kolízie s loďou.
     * Kontroluje či sa loď stretla s centipede.
     */
    private boolean lodKoliziaSCentipede() {
        ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();
        
        for (Centipede c : vsetkyCentipede) {
            for (CastiTela cast : c.getTelo()) {
                if ((this.lod.getX() + 25 > cast.getX()) && (this.lod.getX() < cast.getX() + 15) && (this.lod.getY() + 25 > cast.getY()) && (this.lod.getY() < cast.getY() + 15)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Vyvolávané manžérom, vtedy, kedy hráč hýbe loď šípkami.
     * Kontroluje či sa loď stretla s prekážkou.
     * @param surX
     * @param surY
     */
    private boolean lodKoliziaSPrekazkami(int surX, int surY) { // s kamenmi
        for (Kamen k : this.prekazky.getKamene()) {
            if ((surX + 25 > k.getX()) && (surX < k.getX() + 15) && (surY + 25 > k.getY()) && (surY < k.getY() + 15)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Vyvolávané metódou na kontrolu kolízie s loďou.
     * Kontroluje či loď nevyšla z hracej plochy.
     */
    public boolean jeMimoMapy() {
        return (this.lod.getX() < -5 || this.lod.getX() > 775 || this.lod.getY() > 675 || this.lod.getY() <= 500); // -5, 775, na rezervu, inak 0 a 800, 500 je max vrch pre raketu
    }

    public void posunLodHore() {
        if (!this.lodKoliziaSPrekazkami(this.lod.getX(), this.lod.getY() - this.typLode.getRychlostLode())) {
            this.lod.posunHore();
        }
    }

    public void posunLodDole() {
        if (!this.lodKoliziaSPrekazkami(this.lod.getX(), this.lod.getY() + this.typLode.getRychlostLode())) {
            this.lod.posunDole();
        }
    }

    public void posunLodVpravo() {
        if (!this.lodKoliziaSPrekazkami(this.lod.getX() + this.typLode.getRychlostLode(), this.lod.getY())) {
            this.lod.posunVpravo();
        }
    }

    public void posunLodVlavo() {
        if (!this.lodKoliziaSPrekazkami(this.lod.getX() - this.typLode.getRychlostLode(), this.lod.getY())) {
            this.lod.posunVlavo();
        }
    }

    public void skry() {
        this.lod.skry();
        this.ovladanieNaboj.skry();
        this.manazer.prestanSpravovatObjekt(this.ovladanieNaboj);
        this.ovladanieNaboj = null;
    }
}