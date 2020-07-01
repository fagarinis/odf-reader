import java.util.HashMap;
import java.util.Map;

public class OdfApplication {
    public static void main(String[] args) throws Exception {
        Map<String, String> markersMock = new HashMap<String, String>();
        markersMock.put("$prova", "TESTO_SOSTITUITO");
        markersMock.put("$indirizzo", "Via prova 123");

        OdfReader odfReader = new OdfReader("documento_vuoto.odt");

        odfReader.printDocumentNodes();


        odfReader.applyMarkers(markersMock);


        odfReader.insertNewRow("inserita da java------");
        odfReader.insertNewRow("inserita da java---1---");
        odfReader.insertNewRow("inserita da java---2---inserita da java---2---");
        odfReader.printDocumentNodes();

        odfReader.saveDocumentAndClose("result.odt");
    }
}
