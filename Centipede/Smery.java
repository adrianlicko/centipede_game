/**
 * Enum, ktorý obsahuje vlastnosti centipede.
 * 
 * @author Adrián Ličko
 */
public enum Smery {
    HORE(-2),
    DOLE(2),
    VPRAVO(2),
    VLAVO(-2);

    private final int vektor;

    Smery(int vektor) {
        this.vektor = vektor;
    }

    public int getVektor() {
        return this.vektor;
    }
}