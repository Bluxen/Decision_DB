package Classi;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * La classe crea la finestra per avvisare l'utente dell'inserimento di una formula non valida.
 */
public class ErroreGUI extends JFrame {

    private JPanel contentPane;
    private JLabel erroreGUIText = new JLabel("Errore");

    /**
     * Crea il frame della classe.
     */
    public ErroreGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TavolaGUI.class.getResource("/Immagini/icona2.png")));
        setTitle("DDB");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel erroreGUIHeader = new JLabel("Errore");
        erroreGUIHeader.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
        erroreGUIHeader.setHorizontalAlignment(SwingConstants.CENTER);
        erroreGUIHeader.setBounds(0, 14, 434, 31);
        contentPane.add(erroreGUIHeader);

        erroreGUIText.setFont(new Font("DejaVu Sans", Font.PLAIN, 21));
        erroreGUIText.setHorizontalAlignment(SwingConstants.CENTER);
        erroreGUIText.setText("La formula inserita non è valida.");
        contentPane.add(erroreGUIText, BorderLayout.CENTER);

    }
}