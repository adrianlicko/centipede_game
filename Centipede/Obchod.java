import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import fri.shapesge.Obrazok;
import java.util.ArrayList;

public class Obchod {
    private BlokTextu skoreText;
    private Obrazok skoreObrazok;
    private TypLode lod;
    private Obrazok obchodText;
    private BlokTextu aktualnaLodText;
    private Obrazok aktualnaLodObrazok;
    // Obrázky všetkých lodí
    private ArrayList<Obrazok> lodeObrazky;
    // Pole všetkých údajov jednotlivých lodí
    private ArrayList<BlokTextu>[] udajeVsekychLodi;
    private ArrayList<BlokTextu> ocislovanie;
    private BlokTextu stavZakupenia;
    private Menu menu;
    private Hra hra;

    public Obchod(Menu m, Hra h) {
        this.menu = m;
        this.hra = h;

        this.skoreText = new BlokTextu("" + UdajeZoSuboru.getInstancia().getSkore(), 120, 695);
        this.skoreText.zmenFont("Aptos", StylFontu.BOLD, 60);
        this.skoreText.zmenFarbu("darkYellow");
        this.skoreText.zobraz();

        this.skoreObrazok = new Obrazok("pics\\Skore_Store.png", 20, 630);
        this.skoreObrazok.zobraz();

        if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.MODRA)) {
            this.lod = TypLode.MODRA;
        } else if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.CERVENA)) {
            this.lod = TypLode.CERVENA;
        } else if (UdajeZoSuboru.getInstancia().getfarbaLode().equals(TypLode.ZLTA)) {
            this.lod = TypLode.ZLTA;
        } else {
            System.out.println(UdajeZoSuboru.getInstancia().getfarbaLode());
            System.out.println(TypLode.MODRA.getFarbaRakety());
            System.out.println("Zla farba");
        }

        this.obchodText = new Obrazok("pics\\obchodText.png", 240, 50);
        this.obchodText.zobraz();

        this.aktualnaLodText = new BlokTextu("Aktuálne zvolená vesmírna loď:", 370, 700);
        this.aktualnaLodText.zmenFont("Aptos", StylFontu.BOLD, 20);
        this.aktualnaLodText.zmenFarbu("black");
        this.aktualnaLodText.zobraz();

        this.aktualnaLodObrazok = new Obrazok(this.lod.getObchodCestaKObrazku(), 690, 610);
        this.aktualnaLodObrazok.zobraz();

        this.lodeObrazky = new ArrayList<Obrazok>();

        for (int i = 0; i < TypLode.values().length; i++) {
            TypLode l = TypLode.values()[i];

            this.lodeObrazky.add(new Obrazok(l.getObchodCestaKObrazku(), 120, (120*i)+200));
        }
        this.udajeVsekychLodi = new ArrayList[this.lodeObrazky.size()];

        this.ocislovanie = new ArrayList<BlokTextu>();
        for (int i = 0; i < this.lodeObrazky.size(); i++) {
            this.ocislovanie.add(new BlokTextu("" + (i+1) + "."));
            this.ocislovanie.get(i).zmenFont("Showcard Gothic", StylFontu.BOLD, 60);
            this.ocislovanie.get(i).zmenFarbu("black");
        }

        for (int i = 0; i < this.udajeVsekychLodi.length; i++) {
            TypLode l = TypLode.values()[i];
            TypPavuka p = TypPavuka.values()[i];

            this.udajeVsekychLodi[i] = new ArrayList<BlokTextu>();
            this.udajeVsekychLodi[i].add(new BlokTextu("Rýchlosť pohybu: " + l.getRychlostRakety()));
            this.udajeVsekychLodi[i].add(new BlokTextu("Životy: " + l.getZivotyRakety()));
            this.udajeVsekychLodi[i].add(new BlokTextu("Rýchlosť striel: " + l.getRychlostNaboja()));
            this.udajeVsekychLodi[i].add(new BlokTextu("Rýchlosť pavúka: " + p.getRychlostPavuka()));
            this.udajeVsekychLodi[i].add(new BlokTextu("Cena: " + l.getCenaLode()));
        }

        this.vykresli();
    }

    public void kupLod1() {
        if (this.hra == null) {
            this.kupLod(TypLode.MODRA);
        }
    }

    public void kupLod2() {
        if (this.hra == null) {
            this.kupLod(TypLode.CERVENA);
        }
    }

    public void kupLod3() {
        if (this.hra == null) {
            this.kupLod(TypLode.ZLTA);
        }
    }

    public void kupLod(TypLode l) {
        if (this.stavZakupenia != null) {
            this.stavZakupenia.skry();
            this.stavZakupenia = null;
        }

        if (UdajeZoSuboru.getInstancia().getSkore() >= l.getCenaLode()) {
            UdajeZoSuboru.getInstancia().odpocitajSkore(l.getCenaLode());
            UdajeZoSuboru.getInstancia().setFarbaLode(l.getFarbaRakety());
            this.aktualnaLodObrazok.zmenObrazok(l.getObchodCestaKObrazku());

            this.skoreText.zmenText("" + UdajeZoSuboru.getInstancia().getSkore());

            this.stavZakupenia = new BlokTextu("Zakúpil si novú vesmírnu loď", 150, 650);
            this.stavZakupenia.zmenFarbu("green");
        } else {
            this.stavZakupenia = new BlokTextu("Na danú loď nemáš dostatok skóre", 150, 650);
            this.stavZakupenia.zmenFarbu("green");
        }
        this.stavZakupenia.zmenFont("Aptos", StylFontu.BOLD, 20);
        this.stavZakupenia.zobraz();
    }

    public void vykresli() {
        int ocislovanieY = 265;
        int surX = 250;
        for (int i = 0; i < this.udajeVsekychLodi.length; i++) {
            this.lodeObrazky.get(i).zobraz();

            this.ocislovanie.get(i).zmenPolohu(60, ocislovanieY);
            this.ocislovanie.get(i).zobraz();
            ocislovanieY += 125;

            int surY = 100;
            for (int j = 0; j < this.udajeVsekychLodi[i].size(); j++) {
                BlokTextu b = this.udajeVsekychLodi[i].get(j);

                if (j == this.udajeVsekychLodi[i].size() - 1) {
                    b.zmenFont("Showcard Gothic", StylFontu.BOLD, 15);
                    b.zmenFarbu("darkYellow");
                    b.zmenPolohu(surX, (120*i)+125+surY);
                    b.zobraz();
                    continue;
                }

                b.zmenFont("Aptos", StylFontu.BOLD, 15);
                b.zmenFarbu("black");
                b.zmenPolohu(surX, (120*i)+125+surY);
                b.zobraz();
                surY += 20;
            }
        }
    }

    public void escUkoncenie() {
        this.skry();
        this.menu.vypniObchod();
    }

    public void skry() {
        if (this.stavZakupenia != null) {
            this.stavZakupenia.skry();
        }

        for (int i = 0; i < this.udajeVsekychLodi.length; i++) {
            this.ocislovanie.get(i).skry();
            for (int j = 0; j < this.udajeVsekychLodi[i].size(); j++) {
                BlokTextu b = this.udajeVsekychLodi[i].get(j);
                b.skry();
            }
            this.lodeObrazky.get(i).skry();
        }
        this.ocislovanie = null;
        this.lodeObrazky = null;
        this.udajeVsekychLodi = null;

        this.aktualnaLodObrazok.skry();
        this.aktualnaLodObrazok = null;
        
        this.aktualnaLodText.skry();
        this.aktualnaLodText = null;

        this.obchodText.skry();
        this.obchodText = null;

        this.lod = null;

        this.skoreObrazok.skry();
        this.skoreObrazok = null;

        this.skoreText.skry();
        this.skoreText = null;
    }
}