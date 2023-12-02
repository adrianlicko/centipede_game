import java.util.ArrayList;

public class OvladanieCentipede {
    private ArrayList<Centipede> centipede; //Zoznam jednotlivých stonožiek, každá stonožka obsahuje zoznam tela
    private Smery smer;
    private Prekazky prekazky;
    private int[] prekazkySurX;
    private int[] prekazkySurY;

    public OvladanieCentipede(int dlzka, Prekazky instancia) {
        this.centipede = new ArrayList<Centipede>();
        this.centipede.add(new Centipede(dlzka));

        this.smer = smer.ZIADEN;

        this.prekazky = instancia; //Trieda Hra si vytvorí inštanciu na Prekážky, pošle správu na ich vygenerovanie a až potom sa vytvorí inštancia na OvladanieCentipede. Proste aby sa mysleli rovnaké prekážky
    }

    public void ovladanieCentipedePocitacom() {
        this.centipede.get(0).vykresli();
        this.centipede.get(0).posunDole();
        this.centipede.get(0).posunDole(); // 2 posuny dole, pretože jej spawn je nad plátnom

        for (;;) { // Nekonečný pohyb stonožky

            // Dočásne riešenie rýchlosti a plynulosti pohybu stonožky
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Smery predosliSmer = this.centipede.get(0).historiaPohybu.get(this.centipede.get(0).historiaPohybu.size()-1);
            Smery predPredosliSmer = Smery.ZIADEN;
            //if (this.centipede.get(0).historiaPohybu.size() >= 2) nikdy nenastane ze by bolo mensie ako 2, lebo ho na zaciatku metody posuvam automaticky 2x dole
            predPredosliSmer = this.centipede.get(0).historiaPohybu.get(this.centipede.get(0).historiaPohybu.size()-2);

            // Následujúci pohyb stonožky na základe predošlého. Bez riešenia či stonožka narazila do prekážky alebo koncu mapy.
            switch (predosliSmer) {
                case HORE:
                    break;
                case DOLE:
                    if (predPredosliSmer == Smery.VLAVO)
                        this.centipede.get(0).posunVpravo();
                    else
                        this.centipede.get(0).posunVlavo();
                    break;
                case VPRAVO:
                    this.centipede.get(0).posunVpravo();
                    break;
                case VLAVO:
                    this.centipede.get(0).posunVlavo();
                    break;
                default:
                    break;
            }

            this.prekazkySurX = this.prekazky.getVsetkySurX();
            this.prekazkySurY = this.prekazky.getVsetkySurY();

            // Riešenie "nárazu" hlavy (presnejšie povedané, či sa hlava nachádza v prekážke alebo mimo mapy)
            for (int i = 0; i < this.prekazkySurX.length; i++) {
                // Podmienka či sa hlava nachádza v prekážke alebo mimo mapy
                if ((this.centipede.get(0).getSurHlavyX() == this.prekazkySurX[i] && this.centipede.get(0).getSurHlavyY() == this.prekazkySurY[i]) || this.centipede.get(0).getSurHlavyX() < 0 || this.centipede.get(0).getSurHlavyX() > 780) {
                    int novaPoziciaHlavyX;
                    int novaPoziciaHlavyY;
                    
                    // Ak sa hlava nachádza v prekážke alebo mimo mapy, tak sa posunie o krok späť a dole, telo neurobí nič. To spraví dojem, že stonožka do prekážky nikdy nenarazila ale hneď sa posunula dole.
                    if (predosliSmer == Smery.VLAVO)
                        novaPoziciaHlavyX = this.centipede.get(0).getSurHlavyX() + 20;
                    else if (predosliSmer == Smery.VPRAVO)
                        novaPoziciaHlavyX = this.centipede.get(0).getSurHlavyX() - 20;
                    else
                        novaPoziciaHlavyX = this.centipede.get(0).getSurHlavyX();

                    // V Triede Prekážky je generácia prekážok spravená tak, že ak stonožka narazí do nej alebo do konca mapy, tak sa bude môcť vždy posunúť nižšie. (Prekážka sa nikdy nebude nachádzať priamo pod miestom, kde by mohla stonožka naraziť)
                    novaPoziciaHlavyY = this.centipede.get(0).getSurHlavyY() + 20;

                    this.centipede.get(0).zmenPoziciuHlavy(novaPoziciaHlavyX, novaPoziciaHlavyY);
                    this.centipede.get(0).setHistoriaPohybu(this.centipede.get(0).historiaPohybu.size()-1, Smery.DOLE);
                }

            }
            // TODO: Nevie sa radovo pohnúť dole!!!!!!!!!!
        }
    }
}