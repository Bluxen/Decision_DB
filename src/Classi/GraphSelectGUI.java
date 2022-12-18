package Classi;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

/**
 * La classe crea la finestra che permette all'utente di decidere se visualizzare tutti i passi di semplificazione del grafico oppure no.
 */
public class GraphSelectGUI extends JFrame {

    private JPanel contentPane;
    private JButton noButton;
    private JButton yesButton;
    private String[] colonne;
    private String[][] truthTable;
    private String logic;

    /**
     * Crea il frame della classe.
     */
    public GraphSelectGUI(String[] cln, String[][] TT, String l) {
        colonne = cln;
        truthTable = TT;
        logic = l;
        setIconImage(Toolkit.getDefaultToolkit().getImage(mainGUI.class.getResource("/Immagini/icona2.png")));
        setTitle("DDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("<html>Visualizzare i passaggi di <br/> semplificazione del grafico?<html>");
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 15, 434, 81);
        contentPane.add(lblNewLabel);

        noButton = new JButton("No");
        noButton.setToolTipText("Verra' mostrato solamente il grafico dopo tutti i passaggi di semplificazione.");
        noButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        noButton.setBounds(60, 125, 100, 44);
        noButton.addActionListener(new ButtonAction());
        contentPane.add(noButton);
        noButton.setFocusable(false);

        yesButton = new JButton("Sì");
        yesButton.setToolTipText("Verranno mostrati i passaggi di semplificazione del grafico.");
        yesButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        yesButton.setBounds(270, 125, 100, 44);
        yesButton.addActionListener(new ButtonAction());
        contentPane.add(yesButton);
        yesButton.setFocusable(false);

    }

    private class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == noButton) {
                //crea il grafico semplificato
                GraficoGUI graph = new GraficoGUI(colonne, truthTable, logic, false,0);
                closeFrame();
            } else if(e.getSource() == yesButton) {
                //crea tutti i tipi di grafico
                GraficoGUI graph = new GraficoGUI(colonne, truthTable, logic, true, 4);
                GraficoGUIReduce graphReduce = new GraficoGUIReduce(colonne, truthTable, logic, 3);
                GraficoGUIApply graphApply = new GraficoGUIApply(colonne, truthTable, logic, 2);
                GraficoGUICX graphCX = new GraficoGUICX(colonne, truthTable, logic, 1);
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
