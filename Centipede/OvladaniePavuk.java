import java.util.ArrayList;
import java.util.Random;

public class OvladaniePavuk {
    private ArrayList<Pavuk> pavuci;
    private Prekazky prekazky;
    private Random random;
    private TypPavuka typPavuka;

    public OvladaniePavuk(Prekazky p, TypPavuka pa) {
        this.pavuci = new ArrayList<Pavuk>();
        this.prekazky = p;
        this.typPavuka = pa;
        this.random = new Random();
        
        switch (random.nextInt(2)) {
            case 0:
                this.novyPavuk();
                break;
            case 1:
                this.novyPavuk();
                this.novyPavuk();
                break;
        }
    }

    public void novyPavuk() {
        int randomY = random.nextInt(500, 600); // Spawn pavúka na Y súradniciach od 500 do 600 v párnych číslach
        if (randomY % 2 != 0) {
            randomY++;
        }

        switch (random.nextInt(2)) { // 50% náhodnosť na zvolenie smeru vpravo alebo vľavo, určuje ktorým smerom sa pavúk bude uberať celú hru
            case 0:
                this.pavuci.add(new Pavuk(this.typPavuka, -30, randomY));
                this.pavuci.get(this.pavuci.size() - 1).posunVpravo();
                break;
            case 1:
                this.pavuci.add(new Pavuk(this.typPavuka, 830, randomY));
                this.pavuci.get(this.pavuci.size() - 1).posunVlavo();
                break;
        }
        this.pavuci.get(this.pavuci.size() - 1).posunHore();
        this.pavuci.get(this.pavuci.size() - 1).vykresli();
    }

    public void ovladajPavuka() {
        for (Pavuk pavuk : this.pavuci) {

            if (pavuk.getPocitadloVodorovne() == 0) {
                if (random.nextInt(2) == 0) { // 50% šanca, že sa pavúk pohne vpravo alebo vľavo
                    pavuk.setPocitadloVodorovne(19);
                } else {
                    pavuk.setPocitadloVodorovne(-19);
                }
            }
            if (pavuk.getPocitadloVodorovne() > 0) {
                switch (pavuk.getPoslednyVodorovnyPohyb()) {
                    case VPRAVO:
                        if (pavuk.getX() >= 800) {
                            pavuk.zmenPoziciu(-30, pavuk.getY());
                        }
                        pavuk.posunVpravo();
                        pavuk.odpocitajPocitadloVodorovne();
                        break;
                    case VLAVO:
                        if (pavuk.getX() <= -30) {
                            pavuk.zmenPoziciu(830, pavuk.getY());
                        }
                        pavuk.posunVlavo();
                        pavuk.odpocitajPocitadloVodorovne();
                        break;
                }
            } else {
                pavuk.pripocitajPocitadloVodorovne();
            }

            

            if (pavuk.getPocitadloZvisle() == 0) {
                if (random.nextInt(5) == 0) { // 20% šanca, že sa pavúk pohne hore alebo dole
                    pavuk.setPocitadloZvisle(19);
                } else {
                    pavuk.setPocitadloZvisle(-19);
                }
            }
            if (pavuk.getPocitadloZvisle() > 0) {
                switch (pavuk.getPoslednyZvislyPohyb()) {
                    case HORE:
                        if (pavuk.getY() <= 450) {
                            pavuk.posunDole();
                        } else {
                            pavuk.posunHore();
                        }
                        pavuk.odpocitajPocitadloZvisle();
                        break;
                    case DOLE:
                        if (pavuk.getY() >= 670) {
                            pavuk.posunHore();
                        } else {
                            pavuk.posunDole();
                        }
                        pavuk.odpocitajPocitadloZvisle();
                        break;
                }
            } else {
                pavuk.pripocitajPocitadloZvisle();
            }
        }
    }
    
    public ArrayList<Pavuk> getVsetkyPavuci() {
        return this.pavuci;
    }
}