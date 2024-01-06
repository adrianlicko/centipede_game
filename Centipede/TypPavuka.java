/**
 * Enum, ktorý obsahuje vlastnosti pavúka.
 * 
 * @author Adrián Ličko
 */
public enum TypPavuka {
    MODRA("pics\\SpiderBlue.png", 3),
    CERVENA("pics\\SpiderRed.png", 2),
    ZLTA("pics\\SpiderYellow.png", 1);
    
    private final String cestaKObrazku;
    private final int rychlostPavuka;
    
    TypPavuka(String cestaKObrazku, int rychlostPavuka) {
        this.cestaKObrazku = cestaKObrazku;
        this.rychlostPavuka = rychlostPavuka;
    }
    
    public String getCestaKObrazku() {
        return this.cestaKObrazku;
    }
    
    public int getRychlostPavuka() {
        return this.rychlostPavuka;
    }
}