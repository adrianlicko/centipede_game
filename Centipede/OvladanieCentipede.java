import java.util.ArrayList;
import java.util.Random;

public class OvladanieCentipede {
    private ArrayList<Centipede> centipede;
    private Centipede.Smery smer;
    private Random random;
    
    public OvladanieCentipede(int dlzka) {
        this.centipede = new ArrayList<Centipede>();
        this.centipede.add(new Centipede(dlzka));
        
        this.smer = smer.ZIADEN;
    }
    
    public void ovladanieCentipedePocitacom() {
        this.random = new Random();
        
        this.centipede.get(0).posunDole();
        for (;;) {
            int smer = random.nextInt(4); // 0-HORE, 1-DOLE, 2-VPRAVO, 3-VLAVO, default-ZIADEN
            System.out.println(this.centipede.get(0).historiaPohybu.size());
            
            switch (this.centipede.get(0).historiaPohybu.get(this.centipede.get(0).historiaPohybu.size()-1)) {
                case HORE:
                    break;
                case DOLE:
                    
                    break;
                case VPRAVO:
                    break;
                case VLAVO:
                    break;
                default:
                    break;
            }
        }
    }
}