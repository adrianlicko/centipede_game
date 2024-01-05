import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class UdajeZoSuboru {
    private static UdajeZoSuboru instancia = null;
    private static String zadaneMeno;
    
    private UdajeZoSuboru() {
        
    }
    
    public static int getSkore() {
        String cestaKSuboru = "udaje.txt";

        try {
            // Čítanie existujúcich hodnôt zo súboru
            Map<String, Integer> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Získanie skóre pre dané meno
            return skoreMapa.getOrDefault(zadaneMeno, 0);
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
            Map<String, Integer> skoreMapa = citajSkoreZoSuboru(cestaKSuboru);

            // Pripočítanie nových hodnôt
            int existujuceSkore = skoreMapa.getOrDefault(zadaneMeno, 0);
            int noveSkore = existujuceSkore + hodnota;
            skoreMapa.put(zadaneMeno, noveSkore);

            // Zapísanie hodnôt späť do súboru
            zapisSkoreDoSuboru(cestaKSuboru, skoreMapa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> citajSkoreZoSuboru(String cesta) throws IOException {
        Map<String, Integer> skoreMapa = new HashMap<>();

        try (BufferedReader citac = new BufferedReader(new FileReader(cesta))) {
            String riadok;
            while ((riadok = citac.readLine()) != null) {
                String[] casti = riadok.split(" = ");
                if (casti.length == 2) {
                    String menoHraca = casti[0];
                    int skore = Integer.parseInt(casti[1]);
                    skoreMapa.put(menoHraca, skore);
                }
            }
        }

        return skoreMapa;
    }

    private static void zapisSkoreDoSuboru(String cesta, Map<String, Integer> skoreMapa) throws IOException {
        try (BufferedWriter zapisovac = new BufferedWriter(new FileWriter(cesta))) {
            for (Map.Entry<String, Integer> zaznam : skoreMapa.entrySet()) {
                zapisovac.write(zaznam.getKey() + " = " + zaznam.getValue());
                zapisovac.newLine();
            }
        }
    }
    
    public static UdajeZoSuboru getInstancia() {
        if (instancia == null) {
            instancia = new UdajeZoSuboru();
        }
        return instancia;
    }
}