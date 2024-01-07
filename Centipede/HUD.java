import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import fri.shapesge.Obrazok;
import java.util.ArrayList;
import fri.shapesge.Obdlznik;

/**
 * Singleton trieda, zodpovedná za vytvorenie a zobrazenie HUD.
 * Zobrazuje počet životov a počet skóre získaného v danej hre.
 * 
 * @author Adrián Ličko
 */
class HUD {
    private static HUD instancia = null;
    private static Obdlznik obdlznik;
    private static int skore;
    private static BlokTextu skoreText;
    private static Obrazok skoreObrazok;
    private static int pocetZivotov;
    private static ArrayList<Obrazok> zivoty;
    
    private HUD() {
        obdlznik = new Obdlznik(0, 700);
        obdlznik.zmenFarbu("black");
        obdlznik.zmenStrany(800, 30);
        obdlznik.zobraz();
        
        skore = 0;
        skoreText = new BlokTextu("" + skore, 720, 725);
        skoreText.zmenFont("Arial", StylFontu.BOLD, 30);
        skoreText.zmenFarbu("yellow");
        
        skoreObrazok = new Obrazok("pics\\Skore.png", 675, 699);
        
        pocetZivotov = 0;
        zivoty = new ArrayList<Obrazok>();
    }
    
    public static void setDefaultHodnoty() {
        skore = 0;
        pocetZivotov = 0;
    }
    
    public static int getSkore() {
        return skore;
    }
    
    public static void pridajSkore(int hodnota) {
        skore += hodnota;
        skoreText.zmenText("" + Integer.toString(skore));
    }
    
    public static void odoberSkore(int hodnota) {
        skore -= hodnota;
        skoreText.zmenText("" + Integer.toString(skore));
    }
    
    public static void zobrazSkore() {
        obdlznik.zobraz();
        skoreText.zobraz();
        skoreObrazok.zobraz();
    }
    
    public static int getPocetZivotov() {
        return pocetZivotov;
    }
    
    public static void pridajZivoty(int hodnota) {
        pocetZivotov += hodnota;
        zobrazZivoty();
    }
    
    public static void uberZivoty() {
        pocetZivotov -= 1;
        zobrazZivoty();
    }
    
    public static void zobrazZivoty() {
        if (!zivoty.isEmpty()) {
            for (Obrazok o : zivoty) {
                o.skry();
            }
            zivoty.clear();
        }
        
        for (int i = 0; i < pocetZivotov; i++) {
            zivoty.add(new Obrazok("pics\\heart.png", (i * 30), 700));
            zivoty.get(zivoty.size() - 1).zobraz();
        }
    }
    
    public static void skry() {
        skoreText.zmenText("0");
        skoreText.skry();
        skoreObrazok.skry();
        obdlznik.skry();
        
        for (Obrazok o : zivoty) {
            o.skry();
        }
    }
    
    public static HUD getInstancia() {
        if (instancia == null) {
            instancia = new HUD();
        }
        return instancia;
    }
}