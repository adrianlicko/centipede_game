public enum TypPavuka {
    MODRA("pics\\SpiderBlue.png", 3),
    CERVENA("pics\\SpiderRed.png", 2),
    ZLTA("pics\\SpiderYellow.png", 1);
    
    private String cestaKObrazku;
    private int rychlostPavuka;
    
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