import fri.shapesge.Obrazok;
import fri.shapesge.Manazer;
import javax.swing.JOptionPane;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

public class Menu {
    private Obrazok nazovHryButton;
    private Obrazok hraButton;
    private Obrazok obchodButton;
    private Manazer manazer;
    private Hra hra;
    private String menoHraca;
    private BlokTextu stav;

    public Menu() {
        this.menoHraca = JOptionPane.showInputDialog("Zadajte meno hráča. Je dôležité si ho zapamätať!");
        while (true) {
            // == null kvoli ak uzivatel stlaci cancel, .length() == 0 ak stlaci ok
            if (this.menoHraca == null) {
                this.menoHraca = JOptionPane.showInputDialog("Zadajte meno hráča. Je dôležité si ho zapamätať!");
            } else if (this.menoHraca.length() == 0) {
                this.menoHraca = JOptionPane.showInputDialog("Zadajte meno hráča. Je dôležité si ho zapamätať!");
            } else {
                UdajeZoSuboru.getInstancia().setMeno(this.menoHraca);
                break;
            }
        }
        
        this.nazovHryButton = new Obrazok("pics\\nazovHryButton.png", 200, 50);
        this.hraButton = new Obrazok("pics\\hraButton.png", 275, 270);
        this.obchodButton = new Obrazok("pics\\obchodButton.png", 275, 400);
        this.vykresli();
    }
    
    public void spustiHruCentipede() {
        if (this.hra == null) {
            this.skry();
            this.hra = new Hra(this, this.menoHraca);
            this.hra.spustiHru(10, 70);
        }
    }
    
    public void vypniHruCentipede(String stav) {
        this.manazer.prestanSpravovatObjekt(this);
        this.hra = null;
        this.vykresli();
        
        if (this.stav == null) {
            this.stav = new BlokTextu(stav);
            this.stav.zmenFont("Arial", StylFontu.BOLD, 30);
        } else {
            this.stav.zmenText(stav);
        }
        
        if (stav.equals("vyhra")) {
            this.stav.zmenPolohu(75, 700);
            this.stav.zmenText("Výhra, svoje aktuálne skóre nájdeš v obchode.");
            this.stav.zmenFarbu("green");
        } else {
            this.stav.zmenPolohu(120, 700);
            this.stav.zmenText("Prehra, tvoje skóre sa ti nepripočítalo.");
            this.stav.zmenFarbu("red");
        }
        this.stav.zobraz();
    }
    
    public void spustiObchod() {
        
    }
    
    public void vykresli() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        
        this.nazovHryButton.zobraz();
        this.hraButton.zobraz();
        this.obchodButton.zobraz();
    }
    
    public void skry() {
        this.obchodButton.skry();
        
        this.hraButton.skry();
        
        this.nazovHryButton.skry();
    }
    
    public void skryTL() {
        this.obchodButton.skry();
    }
}