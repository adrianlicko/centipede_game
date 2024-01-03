public enum TypLode
{
    MODRA("pics\\DarkBlueSpaceShip_player.png", 6, 3, "blue", 3, 1),
    CERVENA("pics\\DarkRedSpaceShip_player.png", 4, 5, "red", 6, 1),
    ZLTA("pics\\DarkYellowSpaceShip_player.png", 8, 1, "darkYellow", 2, 1);
    
    private String cestaKObrazku;
    private int rychlostRakety;
    private int zivotyRakety;
    private String farbaNaboja;
    private int rychlostNaboja;
    private int damageNaboja;

    TypLode(String cestaKObrazku, int rychlostRakety, int zivotyRakety, String farbaNaboja, int rychlostNaboja, int damageNaboja) {
        this.cestaKObrazku = cestaKObrazku;
        this.rychlostRakety = rychlostRakety;
        this.zivotyRakety = zivotyRakety;
        this.farbaNaboja = farbaNaboja;
        this.rychlostNaboja = rychlostNaboja;
        this.damageNaboja = damageNaboja;
    }
    
    public String getCestaKObrazku() {
        return this.cestaKObrazku;
    }

    public int getRychlostRakety() {
        return this.rychlostRakety;
    }
    
    public int getZivotyRakety() {
        return this.zivotyRakety;
    }
    
    public void uberZivotRakety() {
        this.zivotyRakety -= 1;
    }
    
    public String getFarbaNaboja() {
        return this.farbaNaboja;
    }
    
    public int getRychlostNaboja() {
        return this.rychlostNaboja;
    }
    
    public int getDamageNaboja() {
        return this.damageNaboja;
    }
}
