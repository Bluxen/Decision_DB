package Classi;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

/**
 * La classe crea e mostra l'albero di decisione ternaria rappresentante la formula logica inserita dopo la semplificazione di nodi uguali,
 * ossia l'algoritmo reduce, che unisce i nodi con gli stessi archi che puntano agli stessi figli.
 */
public class GraficoGUIReduce {

    //crea il grafico di partenza
    Graph graph = new MultiGraph("ReduceGraph");
    private int nodeID = 0;
    private int edgeID = 0;
    private List<String[]> nodi = new ArrayList<String[]>();

    /**
     * Crea l'albero a partire dalla tavola di verita' data come parametro.
     * @param cln L'array che contiene i nomi delle proposizioni.
     * @param TT L'array bidimensionale che rappresenta la tavola di verita' della formula logica.
     * @param l Il tipo di logica utilizzata (Kleene, Łukasiewicz o Gödel).
     * @param num Il numero del grafico nei passaggi di semplificazione. Se 0 non viene inserito.
     */
    public GraficoGUIReduce(String[] cln, String[][] TT, String l, int num) {

        System.setProperty("org.graphstream.ui", "swing");

        //imposta lo stile generale del grafico
        graph.setAttribute("ui.stylesheet", "node {"
                + "size: 33px, 33px;"
                + "shape: circle;"
                + "fill-color: white;"
                + "stroke-mode: plain;"
                + "stroke-color: black;"
                + "stroke-width: 2px;"
                + "text-size: 25;"
                + "text-offset: 0,-5;"
                + "text-alignment: center;"
                + "}");
        graph.setAttribute("ui.antialias");

        //crea il visualizzatore del grafico
        Viewer viewer = graph.display();

        //inserisce la formula nel frame
        Node Formula = graph.addNode("Formula");
        Formula.setAttribute("label", cln[cln.length - 1]);
        graph.setAttribute("ui.stylesheet","node#Formula {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-color: white;"
                + "}");
        Formula.setAttribute("xy",-2.5,2);
        Formula.setAttribute("layout.frozen");
        Formula.setAttribute("layout.weight",1);

        //inserisce la logica utilizzata nel frame
        Node FormulaType = graph.addNode("FormulaType");
        String logica = l;
        FormulaType.setAttribute("label", logica);
        graph.setAttribute("ui.stylesheet","node#FormulaType {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-color: white;"
                + "}");
        FormulaType.setAttribute("xy",-2.5,1.3);
        FormulaType.setAttribute("layout.frozen");
        FormulaType.setAttribute("layout.weight",1);
        graph.addEdge("logica", Formula, FormulaType);
        graph.setAttribute("ui.stylesheet", "edge#logica {"
                + "fill-color: white;"
                + "}");

        //inserisce che tipo di grafico è nel frame
        Node GraphType = graph.addNode("GraphType");
        GraphType.setAttribute("label", "Reduce");
        graph.setAttribute("ui.stylesheet","node#GraphType {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-color: white;"
                + "}");
        GraphType.setAttribute("xy",2.5,2);
        GraphType.setAttribute("layout.frozen");
        GraphType.setAttribute("layout.weight",1);

        //inserisce il numero del grafico
        if(num != 0){
            Node graphNum = graph.addNode("graphNum");
            String numGraph = num + " di 4";
            graphNum.setAttribute("label", numGraph);
            graph.setAttribute("ui.stylesheet","node#graphNum {"
                    + "shape: box;"
                    + "text-size: 30;"
                    + "text-offset: 1,-6;"
                    + "stroke-color: white;"
                    + "}");
            graphNum.setAttribute("xy",2.5,1.3);
            graphNum.setAttribute("layout.frozen");
            graphNum.setAttribute("layout.weight",0);
            graph.addEdge("numGraph", GraphType, graphNum);
            graph.setAttribute("ui.stylesheet", "edge#numGraph {"
                    + "fill-color: white;"
                    + "}");
        }

        int size = cln.length;
        //crea e posiziona i nodi valore
        Node F = graph.addNode("F");
        F.setAttribute("label", "F");
        graph.setAttribute("ui.stylesheet","node#F {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-width: 3px;"
                + "size: 45px, 35px;}");
        F.setAttribute("xy",-size,-size);
        F.setAttribute("layout.frozen");
        F.setAttribute("layout.weight",10);
        Node T = graph.addNode("T");
        T.setAttribute("label", "T");
        graph.setAttribute("ui.stylesheet","node#T {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-width: 3px;"
                + "size: 45px, 35px;}");
        T.setAttribute("xy",0,-size);
        T.setAttribute("layout.frozen");
        T.setAttribute("layout.weight",10);
        Node U = graph.addNode("U");
        U.setAttribute("label", "U");
        graph.setAttribute("ui.stylesheet","node#U {"
                + "shape: box;"
                + "text-size: 30;"
                + "text-offset: -1,-6;"
                + "stroke-width: 3px;"
                + "size: 45px, 35px;}");
        U.setAttribute("xy",size,-size);
        U.setAttribute("layout.frozen");
        U.setAttribute("layout.weight",10);

        //crea e posiziona la radice
        String firstNome = cln[0];
        String firstArray[] = new String[1];
        Node First = graph.addNode(String.valueOf(nodeID));
        First.setAttribute("xy",0,1);
        First.setAttribute("layout.frozen");
        First.setAttribute("layout.weight",10);
        firstArray[0] = String.valueOf(nodeID);
        First.setAttribute("label", firstNome);
        graph.setAttribute("ui.stylesheet", "node#0 {"
                + "size: 40px, 40px;"
                + "text-size: 30;"
                + "text-offset: 1,-6;"
                + "stroke-width: 3px;"
                + "}");
        nodeID++;
        nodi.add(firstArray);

        //crea la lista di array di nodi
        //ogni array contiene lo stesso tipo di nodo
        //ogni posizione nella lista contiene un array
        for(int i = 1; i < cln.length - 1; i++) {
            String nome = cln[i];
            String nodeArray[] = new String[(int) Math.pow(3,i)];
            for(int j = 0; j < Math.pow(3,i); j++) {
                Node N = graph.addNode(String.valueOf(nodeID));
                nodeArray[j] = String.valueOf(nodeID);
                N.setAttribute("label", nome);
                N.setAttribute("layout.weight",8);
                nodeID++;
            }
            nodi.add(nodeArray);
        }

        //crea gli archi fra i nodi proposizione
        for(int i = 0; i < nodi.size() - 1; i++) {
            for(int j = 0; j < nodi.get(i).length; j++) {
                String array1[] = nodi.get(i);
                String array2[] = nodi.get(i+1);
                int posN2 = ((j+1)*3)-3;
                this.connectF(array1[j], array2[posN2]);
                this.connectT(array1[j], array2[posN2+1]);
                this.connectU(array1[j], array2[posN2+2]);
            }
        }

        //crea gli archi delle soluzioni
        String arrayFigli[] = nodi.get(cln.length-2);
        String valCheck = "";
        int j = 0;
        for(int i = 0; i < Math.pow(3,cln.length-1); i = i+3) {
            valCheck = TT[i][cln.length-1];
            if(valCheck == "F") {
                this.connectF(arrayFigli[j], "F");
            } else if(valCheck == "T") {
                this.connectF(arrayFigli[j], "T");
            } else if(valCheck == "U") {
                this.connectF(arrayFigli[j], "U");
            }
            valCheck = TT[i+1][cln.length-1];
            if(valCheck == "F") {
                this.connectT(arrayFigli[j], "F");
            } else if(valCheck == "T") {
                this.connectT(arrayFigli[j], "T");
            } else if(valCheck == "U") {
                this.connectT(arrayFigli[j], "U");
            }
            valCheck = TT[i+2][cln.length-1];
            if(valCheck == "F") {
                this.connectU(arrayFigli[j], "F");
            } else if(valCheck == "T") {
                this.connectU(arrayFigli[j], "T");
            } else if(valCheck == "U") {
                this.connectU(arrayFigli[j], "U");
            }
            j++;
        }

        //semplifica il grafico unendo i nodi con archi e figli uguali (reduce)
        for(int i = nodi.size() - 1; i > 0; i--) {
            String arrayNodi[] = nodi.get(i);
            for(int k = arrayNodi.length - 1; k >= 0; k--) {
                if(!arrayNodi[k].equals("")) {
                    Node K = graph.getNode(arrayNodi[k]);
                    Edge unoK = K.getLeavingEdge(0);
                    Edge dueK = K.getLeavingEdge(1);
                    Edge treK = K.getLeavingEdge(2);
                    String edgeSumK = this.edgeIDSum(unoK, dueK, treK);
                    String[] edgeIDPartsK = edgeSumK.split(" ");
                    unoK = graph.getEdge(edgeIDPartsK[0]);
                    dueK = graph.getEdge(edgeIDPartsK[1]);
                    treK = graph.getEdge(edgeIDPartsK[2]);
                    for(int z = k-1; z >= 0; z--) {
                        if(!arrayNodi[z].equals("")) {
                            Node Z = graph.getNode(arrayNodi[z]);
                            Edge unoZ = Z.getLeavingEdge(0);
                            Edge dueZ = Z.getLeavingEdge(1);
                            Edge treZ = Z.getLeavingEdge(2);
                            String edgeSumZ = this.edgeIDSum(unoZ, dueZ, treZ);
                            String[] edgeIDPartsZ = edgeSumZ.split(" ");
                            unoZ = graph.getEdge(edgeIDPartsZ[0]);
                            dueZ = graph.getEdge(edgeIDPartsZ[1]);
                            treZ = graph.getEdge(edgeIDPartsZ[2]);
                            if(unoK.getTargetNode().getId().equals(unoZ.getTargetNode().getId()) && dueK.getTargetNode().getId().equals(dueZ.getTargetNode().getId()) && treK.getTargetNode().getId().equals(treZ.getTargetNode().getId())) {
                                String nodePadre = Z.getEnteringEdge(0).getSourceNode().getId();
                                String edgePadre = Z.getEnteringEdge(0).getId();
                                graph.removeNode(arrayNodi[z]);
                                arrayNodi[z] = "";
                                graph.addEdge(edgePadre, nodePadre, arrayNodi[k], true);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Crea un nuovo arco rappresentante il valore Falso tra due nodi.
     * <p>
     * L'ID dell'arco contiene il suo tipo (in questo caso F).
     * <p>
     * ex. EdgeF1
     * @param A Il primo nodo.
     * @param B il secondo nodo.
     */
    private void connectF(String A, String B) {
        String idF = "EdgeF" + String.valueOf(edgeID);
        Edge E = graph.addEdge(idF, A, B, true);
        String edgeFStyle = "edge#" + idF + "{"
                + "stroke-mode: dots;"
                + "fill-color: red;"
//				+ "arrow-shape: none;"
                + "}";
        E.setAttribute("layout.weight", 1);
        edgeID++;
        graph.setAttribute("ui.stylesheet", edgeFStyle);
    }

    /**
     * Crea un nuovo arco rappresentante il valore Vero tra due nodi.
     * <p>
     * L'ID dell'arco contiene il suo tipo (in questo caso T).
     * <p>
     * ex. EdgeT4
     * @param A Il primo nodo.
     * @param B il secondo nodo.
     */
    private void connectT(String A, String B) {
        String idT = "EdgeT" + String.valueOf(edgeID);
        Edge E = graph.addEdge(idT, A, B, true);
        String edgeTStyle = "edge#" + idT + "{"
                + "fill-color: green;"
//				+ "arrow-shape: none;"
                + "}";
        E.setAttribute("layout.weight", 1);
        edgeID++;
        graph.setAttribute("ui.stylesheet", edgeTStyle);
    }

    /**
     * Crea un nuovo arco rappresentante il valore Indefinito tra due nodi.
     * <p>
     * L'ID dell'arco contiene il suo tipo (in questo caso U).
     * <p>
     * ex. EdgeU25
     * @param A Il primo nodo.
     * @param B il secondo nodo.
     */
    private void connectU(String A, String B) {
        String idU = "EdgeU" + String.valueOf(edgeID);
        Edge E = graph.addEdge(idU, A, B, true);
        String edgeUStyle = "edge#" + idU + "{"
                + "stroke-mode: dashes;"
                + "fill-color: gray;"
//				+ "arrow-shape: none;"
                + "}";
        E.setAttribute("layout.weight", 1);
        edgeID++;
        graph.setAttribute("ui.stylesheet", edgeUStyle);
    }

    /**
     * Ordina in una stringa unica gli ID dei tre archi in uscita di un nodo.
     * @param uno Arco 1.
     * @param due Arco 2.
     * @param tre Arco 3.
     * @return Una stringa con la somma degli ID degli archi in ordine alfabetico.
     */
    private String edgeIDSum(Edge uno, Edge due, Edge tre) {
        String sum = "";
        if(uno.getId().charAt(4) == 'F') {
            sum += uno.getId() + " ";
            if(due.getId().charAt(4) == 'T') {
                sum += due.getId() + " ";
                sum += tre.getId();
            } else {
                sum += tre.getId() + " ";
                sum += due.getId();
            }
        } else if(due.getId().charAt(4) == 'F') {
            sum += due.getId() + " ";
            if(uno.getId().charAt(4) == 'T') {
                sum += uno.getId() + " ";
                sum += tre.getId();
            } else {
                sum += tre.getId() + " ";
                sum += uno.getId();
            }
        } else if(tre.getId().charAt(4) == 'F') {
            sum += tre.getId() + " ";
            if(uno.getId().charAt(4) == 'T') {
                sum += uno.getId() + " ";
                sum += due.getId();
            } else {
                sum += due.getId() + " ";
                sum += uno.getId();
            }
        }
        return sum;
    }

}
