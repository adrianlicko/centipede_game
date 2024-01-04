import fri.shapesge.Manazer;
import java.util.ArrayList;

public class OvladanieRaketa {
    private Raketa raketa;
    private TypLode lod;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private OvladaniePavuk ovladaniePavuk;
    private OvladanieNaboj ovladanieNaboj;
    private Manazer manazer;

    public OvladanieRaketa(Manazer m, Prekazky p, OvladanieCentipede oc, TypLode l, OvladaniePavuk op) {
        this.lod = l;
        this.raketa = new Raketa(this.lod, 370, 670);
        this.raketa.vykresli();
        this.prekazky = p;
        this.ovladanieCentipede = oc;
        this.ovladaniePavuk = op;
        this.ovladanieNaboj = new OvladanieNaboj(this.raketa, this.prekazky, this.ovladanieCentipede, this.lod, this.ovladaniePavuk);
        this.manazer = m;
        this.manazer.spravujObjekt(this.ovladanieNaboj);
    }

    public void kontrolujKolizieSLodou() {
        if (this.lodKoliziaSCentipede() || this.lodKoliziaSPavukom() || this.jeMimoMapy()) {
            this.raketa.zmenPolohu(370, 670);
            HUD.getInstancia().uberZivoty();
        }
    }
    
    public boolean lodKoliziaSPavukom() {
        for (Pavuk p : this.ovladaniePavuk.getVsetkyPavuci()) {
            if ((this.raketa.getX() + 30 > p.getX()) && (this.raketa.getX() < p.getX() + 25) && (this.raketa.getY() + 30 > p.getY()) && (this.raketa.getY() < p.getY() + 25)) {
                System.out.println("umrel s pavukom");
                p.skry();
                this.ovladaniePavuk.getVsetkyPavuci().remove(p);
                this.ovladaniePavuk.novyPavuk();
                return true;
            }
        }
        return false;
    }
    
    public boolean lodKoliziaSCentipede() {
        ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();
        
        for (Centipede c : vsetkyCentipede) {
            for (CastiTela cast : c.getTelo()) {
                if ((this.raketa.getX() + 25 > cast.getX()) && (this.raketa.getX() < cast.getX() + 15) && (this.raketa.getY() + 25 > cast.getY()) && (this.raketa.getY() < cast.getY() + 15)) {
                    System.out.println("umrel s centi");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean lodKoliziaSPrekazkami(int surX, int surY) { // s kamenmi
        for (Kamen k : this.prekazky.getKamene()) {
            if ((surX + 25 > k.getX()) && (surX < k.getX() + 15) && (surY + 25 > k.getY()) && (surY < k.getY() + 15)) {
                System.out.println("umrel s prekazkou");
                return true;
            }
        }
        return false;
    }
    
    public boolean jeMimoMapy() {
        return (this.raketa.getX() < -5 || this.raketa.getX() > 775 || this.raketa.getY() > 675 || this.raketa.getY() <= 500); // -5, 775, na rezervu, inak 0 a 800, 500 je max vrch pre raketu
    }

    public void posunRaketuHore() {
        if (!this.lodKoliziaSPrekazkami(this.raketa.getX(), this.raketa.getY() - this.lod.getRychlostRakety())) {
            this.raketa.posunHore();
        }
    }

    public void posunRaketuDole() {
        if (!this.lodKoliziaSPrekazkami(this.raketa.getX(), this.raketa.getY() + this.lod.getRychlostRakety())) {
            this.raketa.posunDole();
        }
    }

    public void posunRaketuVpravo() {
        if (!this.lodKoliziaSPrekazkami(this.raketa.getX() + this.lod.getRychlostRakety(), this.raketa.getY())) {
            this.raketa.posunVpravo();
        }
    }

    public void posunRaketuVlavo() {
        if (!this.lodKoliziaSPrekazkami(this.raketa.getX() - this.lod.getRychlostRakety(), this.raketa.getY())) {
            this.raketa.posunVlavo();
        }
    }

}