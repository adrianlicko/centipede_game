import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton trieda, služiaca na zapisovanie a čítanie údajov v súbore "udaje.txt".
 * Údaje kažého hráča sú uložené vo formáte "meno = skore, farba, level".
 * Tieto údaje sa zapíšu vtedy kedy sa hráčovy uloží skóre, teda kedy hru vyhrá, keď zničí všetky časti centipede na hracej ploche.
 * "meno" sa zadáva v triede Menu, v konštruktore cez JOptionPane.
 * "skore" sa zapíše keď hru hráč vyhrá s hodnotou ktorú počas hry dosiahol.
 * "farba" je v základe nastavená na modrú, resp. hráč má v základe modrú vesmirnu loď s modrým pavúkom.
 * "level" sa tiež zapíše až vtedy, keď hráč vyhrá hru. Hodnota levelu je posledný prejdený level.
 * 
 * @author Stack Overflow, GeeksForGeeks - značná časť - veľa rôznych diskusií pospájaných dokopy
 * @author ChatGPT - doladenie
 */
class UdajeZoSuboru {
    private static UdajeZoSuboru instancia = null;
    private static String zadaneMeno;

    private UdajeZoSuboru() {

    }
    
    public static void pripocitajLevel() {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Pripočítanie nových hodnôt
            SkoreInfo existujuceSkoreInfo = skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo());
            int existujuciLevel = existujuceSkoreInfo.getLevel();
            int novyLevel = existujuciLevel + 1;
            String farba = existujuceSkoreInfo.getFarba();
            int skore = existujuceSkoreInfo.getSkore();
            skoreMapa.put(zadaneMeno, new SkoreInfo(skore, farba, novyLevel));

            // Zapísanie hodnôt späť do súboru
            zapisSkoreDoSuboru(cestaKSuboru, skoreMapa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int getLevel() {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Získanie levelu pre dané meno
            return skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo()).getLevel();
        } catch (IOException e) {
            e.printStackTrace();
            return 0; // V prípade chyby vrátiť nulu
        }
    }

    public static int getSkore() {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Získanie skóre pre dané meno
            return skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo()).getSkore();
        } catch (IOException e) {
            e.printStackTrace();
            return 0; // V prípade chyby vrátiť nulu
        }
    }

    public static void setMeno(String menoHraca) {
        zadaneMeno = menoHraca;
    }

    public static String getMeno() {
        return zadaneMeno;
    }

    public static void zapisSkore(int hodnota) {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Pripočítanie nových hodnôt
            SkoreInfo existujuceSkoreInfo = skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo());
            int existujuceSkore = existujuceSkoreInfo.getSkore();
            String farba = existujuceSkoreInfo.getFarba();
            int level = existujuceSkoreInfo.getLevel();
            int noveSkore = existujuceSkore + hodnota;
            skoreMapa.put(zadaneMeno, new SkoreInfo(noveSkore, farba, level));

            // Zapísanie hodnôt späť do súboru
            zapisSkoreDoSuboru(cestaKSuboru, skoreMapa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void odpocitajSkore(int hodnota) {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Pripočítanie nových hodnôt
            SkoreInfo existujuceSkoreInfo = skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo());
            int existujuceSkore = existujuceSkoreInfo.getSkore();
            String farba = existujuceSkoreInfo.getFarba();
            int level = existujuceSkoreInfo.getLevel();
            int noveSkore = existujuceSkore - hodnota;
            skoreMapa.put(zadaneMeno, new SkoreInfo(noveSkore, farba, level));

            // Zapísanie hodnôt späť do súboru
            zapisSkoreDoSuboru(cestaKSuboru, skoreMapa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, SkoreInfo> citajSkoreZoSuboru(String cesta) throws IOException {
        Map<String, SkoreInfo> skoreMapa = new HashMap<>();

        try (BufferedReader citac = new BufferedReader(new FileReader(cesta))) {
            String riadok;
            while ((riadok = citac.readLine()) != null) {
                String[] casti = riadok.split(" = ");
                if (casti.length == 2) {
                    String menoHraca = casti[0];
                    SkoreInfo skoreInfo = parseSkoreInfo(casti[1]);
                    skoreMapa.put(menoHraca, skoreInfo);
                }
            }
        }

        return skoreMapa;
    }

    private static void zapisSkoreDoSuboru(String cesta, Map<String, SkoreInfo> skoreMapa) throws IOException {
        try (BufferedWriter zapisovac = new BufferedWriter(new FileWriter(cesta))) {
            for (Map.Entry<String, SkoreInfo> zaznam : skoreMapa.entrySet()) {
                zapisovac.write(zaznam.getKey() + " = " + zaznam.getValue().toString());
                zapisovac.newLine();
            }
        }
    }

    private static SkoreInfo parseSkoreInfo(String skoreInfoStr) {
        String[] casti = skoreInfoStr.split(", ");
        if (casti.length == 3) {
            int skore = Integer.parseInt(casti[0]);
            String farba = casti[1];
            int level = Integer.parseInt(casti[2]);
            return new SkoreInfo(skore, farba, level);
        } else {
            return new SkoreInfo();
        }
    }

    private static class SkoreInfo {
        private int skore;
        private String farba;
        private int level;
        
        public void setFarbaLode(String novaFarba) {
            this.farba = novaFarba;
        }

        public SkoreInfo() {
            this.skore = 0;
            this.farba = TypLode.MODRA.getFarbaLode(); // Default farba
            this.level = 1;
        }

        public SkoreInfo(int skore, String farba, int level) {
            this.skore = skore;
            this.farba = farba;
            this.level = level;
        }

        public int getSkore() {
            return skore;
        }

        public String getFarba() {
            return farba;
        }
        
        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return skore + ", " + farba + ", " + level;
        }
    }
    
    public static void setFarbaLode(String novaFarba) {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Získanie existujúceho SkoreInfo
            SkoreInfo existujuceSkoreInfo = skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo());

            // Aktualizácia farby
            existujuceSkoreInfo.setFarbaLode(novaFarba);

            // Uloženie aktualizovaných hodnôt späť do súboru
            skoreMapa.put(zadaneMeno, existujuceSkoreInfo);
            zapisSkoreDoSuboru(cestaKSuboru, skoreMapa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public TypLode getfarbaLode() {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, SkoreInfo> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Získanie farby pre dané meno
            SkoreInfo skoreInfo = skoreMapa.getOrDefault(zadaneMeno, new SkoreInfo());
            String farba = skoreInfo.getFarba();

            // Prechod cez enum TypLode a hľadanie enumu podľa názvu farby
            for (TypLode typLode : TypLode.values()) {
                if (typLode.getFarbaLode().equals(farba)) {
                    return typLode;
                }
            }

            // Ak farba nenájdená, vrátiť defaultnú farbu
            return TypLode.MODRA;
        } catch (IOException e) {
            e.printStackTrace();
            return TypLode.MODRA; // V prípade chyby vrátiť defaultnú farbu
        }
    }

    public static UdajeZoSuboru getInstancia() {
        if (instancia == null) {
            instancia = new UdajeZoSuboru();
        }
        return instancia;
    }
}
