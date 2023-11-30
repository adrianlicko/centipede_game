import java.util.ArrayList;
import java.util.Random;

public class OvladanieCentipede {
    public Centipede centipede;
    private Random random;
    
    public OvladanieCentipede(int dlzka) {
        this.centipede = new Centipede(dlzka);
        this.centipede.vykresli();
    }
    
    public void ovladanieCentipedePocitacom() {
        this.random = new Random();
        
        this.centipede.posunDole();
        for (;;) {
            int smer = random.nextInt(4); // 0-Hore, 1-Vpravo, 2-Dole, 3-Vlavo
            switch (smer) {
                case 0:
                    
                case 1:
                case 2:
                case 3:
            }
        }
    }
}