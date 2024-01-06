public enum TypLode
{
    MODRA("pics\\DarkBlueSpaceShip_Store.png", "pics\\DarkBlueSpaceShip_player.png", "MODRA", 6, 3, "blue", 3, 0),
    CERVENA("pics\\DarkRedSpaceShip_Store.png", "pics\\DarkRedSpaceShip_player.png", "CERVENA", 4, 5, "red", 6, 150),
    ZLTA("pics\\DarkYellowSpaceShip_Store.png","pics\\DarkYellowSpaceShip_player.png", "ZLTA", 8, 1, "darkYellow", 2, 500);
    
    private String obchodCestaKObrazku;
    private String cestaKObrazku;
    private String farbaRakety;
    private int rychlostRakety;
    private int zivotyRakety;
    private String farbaNaboja;
    private int rychlostNaboja;
    private int cenaLode;

    TypLode(String obchodCestaKObrazku, String cestaKObrazku, String farbaRakety, int rychlostRakety, int zivotyRakety, String farbaNaboja, int rychlostNaboja, int cenaLode) {
        this.obchodCestaKObrazku = obchodCestaKObrazku;
        this.cestaKObrazku = cestaKObrazku;
        this.farbaRakety = farbaRakety;
        this.rychlostRakety = rychlostRakety;
        this.zivotyRakety = zivotyRakety;
        this.farbaNaboja = farbaNaboja;
        this.rychlostNaboja = rychlostNaboja;
        this.cenaLode = cenaLode;
    }
    
    public String getObchodCestaKObrazku() {
        return this.obchodCestaKObrazku;
    }
    
    public String getCestaKObrazku() {
        return this.cestaKObrazku;
    }
    
    public String getFarbaRakety() {
        return this.farbaRakety;
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
    
    public int getCenaLode() {
        return this.cenaLode;
    }
}
