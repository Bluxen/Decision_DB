package Classi;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * La classe crea l'interfaccia grafica che mostra il risultato della formula inserita
 * sotto forma di tavola di verità.
 */
public class TavolaGUI extends JFrame {

    private JPanel contentPane;
    private JLabel TavolaGUIText = new JLabel("Formula risultato");

    /**
     * Crea il frame per la JTable che contiene la tavola di verità.
     */
    public TavolaGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TavolaGUI.class.getResource("/Immagini/icona2.png")));
        setTitle("DDB");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        TavolaGUIText.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        TavolaGUIText.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(TavolaGUIText, BorderLayout.CENTER);
    }

    /**
     * Imposta il titolo del frame.
     * @param s La stringa da impostare come titolo del frame.
     */
    public void setTitolo(String s) {
        setTitle(s);
    }

}

