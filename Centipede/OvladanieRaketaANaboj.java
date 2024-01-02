import java.util.ArrayList;
import java.util.Iterator;

public class OvladanieRaketaANaboj {
    private Raketa raketa;
    private ArrayList<Naboj> naboje;
    private Prekazky prekazky;
    private OvladanieCentipede ovladanieCentipede;

    public OvladanieRaketaANaboj(Prekazky p, OvladanieCentipede o) {
        this.raketa = new Raketa(370, 670);
        this.raketa.vykresli();

        this.naboje = new ArrayList<Naboj>();
        this.prekazky = p;
        
        this.ovladanieCentipede = o;
    }

    public void strel() {
        Naboj n = new Naboj(this.raketa);
        this.naboje.add(n);
        n.zmenPolohu(this.raketa.getX() + 13, this.raketa.getY()); // +13 aby strela vychádzala zo stredu rakety
        n.vykresli();
    }
    
    public void ovladajNaboje() {
        Iterator<Naboj> iterator = this.naboje.iterator();
        while (iterator.hasNext()) {
            Naboj n = iterator.next();
            n.posunHore();
            if (jeKoliziaSPrekazkami(n) || jeKoliziaSCentipede(n) || jeMimoMapy(n)) {
                n.skry();
                iterator.remove();
            }
        }
    }
    
    public boolean jeKoliziaSPrekazkami(Naboj n) {
        ArrayList<Kamen> kamene = this.prekazky.getKamene();
        
        for (Kamen k : kamene) {
            if ((n.getX() + 13 >= k.getX()) && (n.getX() < k.getX() + 7) && (n.getY() + 20 > k.getY()) && (n.getY() < k.getY())) {
                k.skry();
                kamene.remove(k);
                this.prekazky.setKamene(kamene);
                return true;
            }
        }
        return false;
    }
    
    public boolean jeKoliziaSCentipede(Naboj n) {
        ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();
        
        for (Centipede c : vsetkyCentipede) {
            for (CastiTela cast : c.getTelo()) {
                if ((n.getX() + 13 >= cast.getX()) && (n.getX() < cast.getX() + 12) && (n.getY() + 25 > cast.getY()) && (n.getY() < cast.getY())) {
                    
                    int hitIndex = c.getTelo().indexOf(cast);
                    
                    if (c.getTelo().size() == 1) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        
                    } else if (c.getTelo().size() != 1 && (hitIndex == c.getTelo().size() - 1 || hitIndex == 0)) {
                        cast.skry();
                        c.getTelo().remove(cast);
                        
                    } else {
                        this.ovladanieCentipede.novaCentipede(c, c.getTelo().size() - hitIndex - 1, cast.getX(), cast.getY());
                        
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
    
    public boolean jeMimoMapy(Naboj n) {
        return n.getY() <= 0;
    }
    
    /*
    public void ovladajNaboje() {
        for (Naboj n : this.naboje) {
            n.posunHore();

            this.jeKolizia();
        }
    }

    public void jeKolizia() {
        ArrayList<Kamen> kamene = this.prekazky.getKamene();
        
        //Iterator<Kamen> itrKamene = kamene.iterator();
        //Iterator<Naboj> itrNaboje = this.naboje.iterator();

        if (this.naboje.size() != 0) {
            for (Kamen k : kamene) {
            //while (itrKamene.hasNext()) {
                //Kamen k = itrKamene.next();
                for (Naboj n : this.naboje) {
                //while (itrNaboje.hasNext()) {
                    //Naboj n = itrNaboje.next();
                    if ((n.getX() + 13 >= k.getX()) && (n.getX() < k.getX() + 7)) {
                        if ((n.getY() + 20 > k.getY()) && (n.getY() < k.getY())) {
                            n.skry();
                            this.naboje.remove(n);

                            k.skry();
                            this.prekazky.getKamene().remove(k);

                            break;
                        }
                    }
                    
                    if (n.getY() <= 0) {
                        n.skry();
                        this.naboje.remove(n);
                        
                        break;
                    }
                    
                    ArrayList<Centipede> vsetkyCentipede = this.ovladanieCentipede.getVsetkyCentipede();
                    for (Centipede c : vsetkyCentipede) {
                        ArrayList<CastiTela> vsetkyCasti = c.getTelo();
                        for (CastiTela cast : vsetkyCasti) {
                            if ((n.getX() + 13 >= cast.getX()) && (n.getX() < cast.getX() + 12)) {
                                if ((n.getY() + 25 > cast.getY()) && (n.getY() < cast.getY())) {
                                    n.skry();
                                    this.naboje.remove(n);
                                    
                                    //cast.skry();
                                    //vsetkyCasti.remove(cast);
                                    
                                    if (vsetkyCasti.size() == 1) {
                                        cast.skry();
                                        vsetkyCasti.remove(cast);
                                        break;
                                    }
                                    
                                    int hitIndex = vsetkyCasti.indexOf(cast);
                                    for (int i = hitIndex; i < vsetkyCasti.size(); i++) {
                                        vsetkyCasti.get(i).skry();
                                        vsetkyCasti.remove(i);
                                    }
                                    
                                    this.ovladanieCentipede.novaCentipede(c, vsetkyCasti.size() - hitIndex - 1, cast.getX(), cast.getY());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    */

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