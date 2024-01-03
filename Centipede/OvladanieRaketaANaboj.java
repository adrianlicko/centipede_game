import java.util.ArrayList;
import java.util.Iterator;

public class OvladanieRaketaANaboj {
    private Raketa raketa;
    private ArrayList<Naboj> naboje;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;
    private TypLode lod;
    private OvladaniePavuk ovladaniePavuk;

    public OvladanieRaketaANaboj(Prekazky p, OvladanieCentipede oc, TypLode l, OvladaniePavuk op) {
        this.lod = l;
        this.raketa = new Raketa(this.lod, 370, 670);
        this.raketa.vykresli();

        this.naboje = new ArrayList<Naboj>();
        this.prekazky = p;
        
        this.ovladanieCentipede = oc;
        
        this.ovladaniePavuk = op;
    }

    public void strel() {
        Naboj n = new Naboj(this.lod, this.raketa);
        this.naboje.add(n);
        n.zmenPolohu(this.raketa.getX() + 13, this.raketa.getY()); // +13 aby strela vychádzala zo stredu rakety
        n.vykresli();
    }
    
    public void ovladajNaboje() {
        Iterator<Naboj> iterator = this.naboje.iterator();
        while (iterator.hasNext()) {
            Naboj n = iterator.next();
            n.posunHore();
            if (nabojKoliziaSPrekazkami(n) || nabojKoliziaSCentipede(n) || nabojMimoMapy(n) || nabojKoliziaSPavukom(n)) {
                n.skry();
                iterator.remove();
            }
        }
    }
    
    public boolean nabojKoliziaSPrekazkami(Naboj n) {
        ArrayList<Kamen> kamene = this.prekazky.getKamene();
        
        for (Kamen k : kamene) {
            if ((n.getX() + 13 >= k.getX()) && (n.getX() < k.getX() + 7) && (n.getY() + 20 > k.getY()) && (n.getY() < k.getY())) {
                k.skry();
                kamene.remove(k);
                this.prekazky.setKamene(kamene);
                HUD.getInstancia().pridajSkore(2);
                return true;
            }
        }
        return false;
    }
    
    public boolean nabojKoliziaSCentipede(Naboj n) {
        ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();
        
        for (Centipede c : vsetkyCentipede) {
            for (CastiTela cast : c.getTelo()) {
                if ((n.getX() + 13 >= cast.getX()) && (n.getX() < cast.getX() + 12) && (n.getY() + 25 > cast.getY()) && (n.getY() < cast.getY())) {
                    
                    int hitIndex = c.getTelo().indexOf(cast);
                    
                    if (c.getTelo().size() == 1) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        HUD.getInstancia().pridajSkore(10);
                        
                    } else if (c.getTelo().size() != 1 && (hitIndex == c.getTelo().size() - 1)) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        HUD.getInstancia().pridajSkore(5);
                        
                    } else {
                        this.ovladanieCentipede.novaCentipede(c, c.getTelo().size() - hitIndex - 1, cast.getX(), cast.getY());
                        HUD.getInstancia().pridajSkore(5);
                        
                        int pocetCastiNaZmazanie = c.getTelo().size() - hitIndex;
                        for (int i = 0; i < pocetCastiNaZmazanie; i++) {
                            c.getTelo().get(hitIndex).skry();
                            c.getTelo().remove(hitIndex);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean nabojKoliziaSPavukom(Naboj n) {
        for (Pavuk p : this.ovladaniePavuk.getVsetkyPavuci()) {
            if ((n.getX() + 11 >= p.getX() && n.getX() < p.getX() + 14) && (n.getY() + 30 > p.getY()) && (n.getY() < p.getY())) {
                p.skry();
                HUD.getInstancia().pridajSkore(12);
                this.ovladaniePavuk.novyPavuk();
                return true;
            }
        }
        return false;
    }
    
    public boolean nabojMimoMapy(Naboj n) {
        return n.getY() <= 0;
    }

    public void posunRaketuHore() {
        this.raketa.posunHore();
    }

    public void posunRaketuDole() {
        this.raketa.posunDole();
    }

    public void posunRaketuVpravo() {
        this.raketa.posunVpravo();
    }

    public void posunRaketuVlavo() {
        this.raketa.posunVlavo();
    }
}