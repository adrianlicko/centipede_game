/**
 * Singleton, používaný na určenie dĺžky centipede, počtu prekážok, na základe zadaného levelu do parametrov metód.
 * 
 * @author Adrián Ličko
 */
class Levely {
    private static Levely instancia = null;

    private Levely() {
        
    }
    
    public int getDlzkaCentipede(int level) {
        if (level <= 2) {
            return 8;
        } else if (level <= 4) {
            return 12;
        }  else if (level == 5) {
            return 16;
        } else if (level == 6) {
            return 5;
        } else if (level <= 9) {
            return 15;
        } else if (level == 10) {
            return 20;
        }
        
        return 14;
    }
    
    public int getPocetPrekazok(int level) {
        if (level <= 2) {
            return 60;
        } else if (level <= 4) {
            return 70;
        } else if (level == 5) {
            return 45;
        } else if (level == 6) {
            return 90;
        } else if (level <= 9) {
            return 70;
        } else if (level == 10) {
            return 60;
        }
        
        return 70;
    }
    
    public static Levely getInstancia() {
        if (instancia == null) {
            instancia = new Levely();
        }
        return instancia;
    }
}