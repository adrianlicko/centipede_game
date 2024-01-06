/**
 * Enum, obsahujúci všetky typy vesmírnych lodí a ich údajov, vlastností.
 * 
 * @author Adrián Ličko
 */
public enum TypLode
{
    MODRA("pics\\DarkBlueSpaceShip_Store.png", "pics\\DarkBlueSpaceShip_player.png", "MODRA", 6, 3, "blue", 3, 0),
    CERVENA("pics\\DarkRedSpaceShip_Store.png", "pics\\DarkRedSpaceShip_player.png", "CERVENA", 4, 5, "red", 6, 150),
    ZLTA("pics\\DarkYellowSpaceShip_Store.png","pics\\DarkYellowSpaceShip_player.png", "ZLTA", 8, 1, "darkYellow", 2, 500);
    
    private final String obchodCestaKObrazku;
    private final String cestaKObrazku;
    private final String farbaLode;
    private final int rychlostLode;
    private int zivotyLode;
    private final String farbaNaboja;
    private final int rychlostNaboja;
    private final int cenaLode;

    TypLode(String obchodCestaKObrazku, String cestaKObrazku, String farbaLode, int rychlostLode, int zivotyLode, String farbaNaboja, int rychlostNaboja, int cenaLode) {
        this.obchodCestaKObrazku = obchodCestaKObrazku;
        this.cestaKObrazku = cestaKObrazku;
        this.farbaLode = farbaLode;
        this.rychlostLode = rychlostLode;
        this.zivotyLode = zivotyLode;
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
    
    public String getFarbaLode() {
        return this.farbaLode;
    }

    public int getRychlostLode() {
        return this.rychlostLode;
    }
    
    public int getZivotyLode() {
        return this.zivotyLode;
    }
    
    public void uberZivotLode() {
        this.zivotyLode -= 1;
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
