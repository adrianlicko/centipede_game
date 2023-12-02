public class Kolizia {
    private static Kolizia instancia;
    
    private Kolizia() {
        
    }
    
    public static Kolizia getInstancia() {
        if (Kolizia.instancia == null)
            Kolizia.instancia = new Kolizia();
            
        return Kolizia.instancia;
    }
    
    public static boolean jeKolizia(int surX1, int surY1, int surX2, int surY2) {
        if (surX1 == surX2 && surY1 == surY2)
            return true;
        return false;
    }
}