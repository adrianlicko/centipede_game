import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import fri.shapesge.Obrazok;
import java.util.ArrayList;

class HUD {
    private static HUD instancia = null;
    private static int skore;
    private static BlokTextu skoreText;
    private static int pocetZivotov;
    private static ArrayList<Obrazok> zivoty;
    
    private HUD() {
        skore = 0;
        skoreText = new BlokTextu("Skore: " + skore, 650, 725);
        skoreText.zmenFont("Arial", StylFontu.BOLD, 30);
        skoreText.zmenFarbu("red");
        
        pocetZivotov = 0;
        zivoty = new ArrayList<Obrazok>();
    }
    
    public int getSkore() {
        return skore;
    }
    
    public void pridajSkore(int hodnota) {
        skore += hodnota;
        skoreText.zmenText("Skore: " + Integer.toString(skore));
    }
    
    public void odoberSkore(int hodnota) {
        skore -= hodnota;
        skoreText.zmenText("Skore: " + Integer.toString(skore));
    }
    
    public void zobrazSkore() {
        skoreText.zobraz();
    }
    
    public int getPocetZivotov() {
        return pocetZivotov;
    }
    
    public void pridajZivoty(int hodnota) {
        pocetZivotov += hodnota;
        zobrazZivoty();
    }
    
    public void uberZivoty() {
        pocetZivotov -= 1;
        zobrazZivoty();
    }
    
    public void zobrazZivoty() {
        if (!zivoty.isEmpty()) {
            for (Obrazok o : zivoty) {
                o.skry();
            }
            zivoty.clear();
        }
        
        for (int i = 0; i < pocetZivotov; i++) {
            zivoty.add(new Obrazok("pics\\heart.png", (i*30), 700));
            zivoty.get(zivoty.size() - 1).zobraz();
        }
    }
    
    public static HUD getInstancia() {
        if (instancia == null) {
            instancia = new HUD();
        }
        return instancia;
    }
}