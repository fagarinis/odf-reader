import org.odftoolkit.odfdom.doc.OdfDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class OdfReader {

    private OdfDocument odfDocument;
    private Map<String, String> markers = new HashMap<String, String>();

    public OdfReader(String documentPath) throws Exception {
        this.odfDocument = OdfDocument.loadDocument(documentPath);
    }

    public void printDocumentNodes() throws Exception {
        System.out.println("-----START DOCUMENT------");
        this.printNodes(this.odfDocument.getContentRoot().getChildNodes(), 0);
        System.out.println("-----END DOCUMENT------");
        System.out.println();
    }


    public void applyMarkers(Map<String, String> markers) throws Exception {
        this.markers = markers;
        this.substituteMarkers(this.odfDocument.getContentRoot().getChildNodes());
    }

    public void saveDocumentAndClose(String path) throws Exception {
        this.odfDocument.save(path);
        this.odfDocument.close();
    }

    public OdfDocument getOdfDocument() {
        return this.odfDocument;
    }


    private void printNodes(NodeList nodeList, int level) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String str = node.getNodeName() + " value: " + node.getNodeValue();
            printIndentation(level);
            System.out.println(str);

            printNodes(node.getChildNodes(), ++level);
        }
    }

    private void printIndentation(int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
    }

    private void substituteMarkers(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            substituteMarkersInNodeValue(node);

            substituteMarkers(node.getChildNodes());
        }
    }

    private void substituteMarkersInNodeValue(Node node) {
        String nodeString = node.getNodeValue();
        if (nodeString == null) {
            return;
        }
        for (Map.Entry<String, String> entry : this.markers.entrySet()) {
            nodeString = nodeString.replace(entry.getKey(), entry.getValue());
        }
        node.setTextContent(nodeString);
    }

}
