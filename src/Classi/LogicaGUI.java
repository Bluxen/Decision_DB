package Classi;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

/**
 * La classe crea il frame di selezione della logica da utilizzare per la risoluzione della formula.
 * <p>
 * Le logiche possibili sono: Kleene e Priest, Łukasiewicz, e Gödel.
 */
public class LogicaGUI extends JFrame {

    private JPanel contentPane;
    private JButton KleeneButton;
    private JButton LukaButton;
    private JButton GodelButton;

    private String formulaOG;
    private String formulaMOD;
    private ArrayList<Character> listProps;

    private static int coordX = 140;
    private static int coordY = 420;

    /**
     * Crea il frame della classe, e utilizza i parametri per la creazione della classe Solver appropriata, a seconda della logica scelta.
     * @param fo La formula originale di partenza.
     * @param fm La formula a notazione postfissa.
     * @param lp La lista di proposizioni della formula.
     */
    public LogicaGUI(String fo, String fm, ArrayList<Character> lp) {
        formulaOG = fo;
        formulaMOD = fm;
        listProps = lp;
        setIconImage(Toolkit.getDefaultToolkit().getImage(mainGUI.class.getResource("/Immagini/icona2.png")));
        setTitle("DDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Che logica utilizzare?");
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 37, 434, 31);
        contentPane.add(lblNewLabel);

        KleeneButton = new JButton("Kleene");
        KleeneButton.setToolTipText("Utilizza la logica di Kleene e Priest.");
        KleeneButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        KleeneButton.setBounds(20, 125, 100, 44);
        KleeneButton.addActionListener(new ButtonAction());
        contentPane.add(KleeneButton);
        KleeneButton.setFocusable(false);

        LukaButton = new JButton("Łukasiewicz");
        LukaButton.setToolTipText("Utilizza la logica di Łukasiewicz.");
        LukaButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        LukaButton.setBounds(141, 125, 151, 44);
        LukaButton.addActionListener(new ButtonAction());
        contentPane.add(LukaButton);
        LukaButton.setFocusable(false);

        GodelButton = new JButton("Gödel");
        GodelButton.setToolTipText("Utilizza la logica di Gödel.");
        GodelButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        GodelButton.setBounds(315, 125, 100, 44);
        GodelButton.addActionListener(new ButtonAction());
        contentPane.add(GodelButton);
        GodelButton.setFocusable(false);

    }

    /**
     * ActionListener per la selezione della logica da utilizzare.
     */
    private class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(e.getSource() == KleeneButton) {
                    TavolaGUI tableGUI = new TavolaGUI();
                    //crea il formulasolver che segue la logica di Kleene
                    FormulaSolver FS = new FormulaSolverKleene(formulaOG, formulaMOD, listProps);
                    //crea la tavola di verità
                    String[][] truthTable = FS.soluzione();
                    //mostra la tavola di verità
                    String[] columns = FS.getColonne();
                    JTable table = new JTable(truthTable, columns);
                    Font font = new Font("DejaVu Sans", Font.PLAIN, 12);
                    table.setFont(font);
                    table.setRowHeight(30);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setDefaultEditor(Object.class, null);
                    tableGUI.getContentPane().add(new JScrollPane(table));
                    tableGUI.setTitolo(" " + formulaOG + "  |  Kleene");
                    //posiziona il primo TavolaGUI a sinistra del grafico
                    //per poi creare tutti i successivi a cascata
                    tableGUI.setLocation(coordX, coordY);
                    coordY += 32;
                    if(coordY > 1000){
                        coordY = 30;
                    }
                    //mostra la tavola di verità
                    tableGUI.setVisible(true);
                    //crea selettore grafico
                    GraphSelectGUI graphS = new GraphSelectGUI(columns, truthTable, "Kleene");
                    graphS.setVisible(true);
                    //chiude la finestra
                    closeFrame();
                } else if(e.getSource() == LukaButton) {
                    TavolaGUI tableGUI = new TavolaGUI();
                    //crea il formulasolver che segue la logica di Łukasiewicz
                    FormulaSolver FS = new FormulaSolverLuka(formulaOG, formulaMOD, listProps);
                    //crea la tavola di verità
                    String[][] truthTable = FS.soluzione();
                    //mostra la tavola di verità
                    String[] columns = FS.getColonne();
                    JTable table = new JTable(truthTable, columns);
                    Font font = new Font("DejaVu Sans", Font.PLAIN, 12);
                    table.setFont(font);
                    table.setRowHeight(30);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setDefaultEditor(Object.class, null);
                    tableGUI.getContentPane().add(new JScrollPane(table));
                    tableGUI.setTitolo(" " + formulaOG + "  |  Łukasiewicz");
                    //posiziona il primo TavolaGUI a sinistra del grafico
                    //per poi creare tutti i successivi a cascata
                    tableGUI.setLocation(coordX, coordY);
                    coordY += 32;
                    if(coordY > 1000){
                        coordY = 30;
                    }
                    //mostra la tavola di verità
                    tableGUI.setVisible(true);
                    //crea selettore grafico
                    GraphSelectGUI graphS = new GraphSelectGUI(columns, truthTable, "Łukasiewicz");
                    graphS.setVisible(true);
                    //chiude la finestra
                    closeFrame();
                } else if(e.getSource() == GodelButton) {
                    TavolaGUI tableGUI = new TavolaGUI();
                    //crea il formulasolver che segue la logica di Gödel
                    FormulaSolver FS = new FormulaSolverGodel(formulaOG, formulaMOD, listProps);
                    //crea la tavola di verità
                    String[][] truthTable = FS.soluzione();
                    String[] columns = FS.getColonne();
                    JTable table = new JTable(truthTable, columns);
                    Font font = new Font("DejaVu Sans", Font.PLAIN, 12);
                    table.setFont(font);
                    table.setRowHeight(30);
                    table.setFocusable(false);
                    table.setRowSelectionAllowed(false);
                    table.setDefaultEditor(Object.class, null);
                    tableGUI.getContentPane().add(new JScrollPane(table));
                    tableGUI.setTitolo(" " + formulaOG + "  |  Gödel");
                    //posiziona il primo TavolaGUI a sinistra del grafico
                    //per poi creare tutti i successivi a cascata
                    tableGUI.setLocation(coordX, coordY);
                    coordY += 32;
                    if(coordY > 1000){
                        coordY = 30;
                    }
                    //mostra la tavola di verità
                    tableGUI.setVisible(true);
                    //crea selettore grafico
                    GraphSelectGUI graphS = new GraphSelectGUI(columns, truthTable, "Gödel");
                    graphS.setVisible(true);
                    //chiude la finestra
                    closeFrame();
                }
            }  catch(Exception f) {
                //crea la finestra di errore
                ErroreGUI errorGUI = new ErroreGUI();
                errorGUI.setLocationRelativeTo(null);
                errorGUI.setVisible(true);
                //la finestra si chiude automaticamente dopo 5 secondi
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent d) {
                        errorGUI.dispose();
                    }
                });
                timer.start();
                closeFrame();
            }

        }
    }

    /**
     * Il metodo chiude la finestra.
     */
    private void closeFrame() {
        this.dispose();
    }
}
