import java.util.HashMap;
import java.util.Map;

public class OdfApplication {
    public static void main(String[] args) throws Exception {
        Map<String, String> markersMock = new HashMap<String, String>();
        markersMock.put("$prova", "TESTO_SOSTITUITO");
        markersMock.put("$indirizzo", "Via prova 123");

        OdfReader odfReader = new OdfReader("documento1.odt");

        odfReader.printDocumentNodes();


        odfReader.applyMarkers(markersMock);


//        odfReader.printDocumentNodes();




        odfReader.saveDocumentAndClose("documento1_result.odt");
    }
}
