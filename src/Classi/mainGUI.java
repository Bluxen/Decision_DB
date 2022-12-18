package Classi;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

/**
 * La classe crea l'interfaccia grafica principale del programma
 * grazie alla quale l'utente può inserire la formula logica da analizzare.
 * @author Simone Tarenzi
 */
public class mainGUI extends JFrame {

    private JButton notButton;
    private JButton andButton;
    private JButton orButton;
    private JButton implyButton;
    private JButton biconditionalButton;
    private JButton invioButton;

    private JPanel contentPane;
    private JTextField formulaText;

    /**
     * Esegue l'applicazione.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainGUI frame = new mainGUI();
                    frame.toFront();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Crea il frame principale.
     */
    public mainGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(mainGUI.class.getResource("/Immagini/icona2.png")));
        setTitle("DDB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Decision Diagram Builder");
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 12, 434, 31);
        contentPane.add(lblNewLabel);

        formulaText = new JTextField();
        formulaText.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        formulaText.setHorizontalAlignment(SwingConstants.CENTER);
        formulaText.setToolTipText("Inserire formula logica");
        formulaText.setBounds(10, 62, 333, 31);
        formulaText.addKeyListener(new OPListener());
        contentPane.add(formulaText);
        formulaText.setColumns(10);
        formulaText.requestFocus();

        notButton = new JButton("\u00AC");
        notButton.setToolTipText("NOT: si possono utilizzare ! e -");
        notButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        notButton.setBounds(62, 126, 60, 35);
        notButton.addActionListener(new ButtonAction());
        contentPane.add(notButton);
        notButton.setFocusable(false);

        andButton = new JButton("\u2227");
        andButton.setToolTipText("AND: si possono utilizzare  &, @, * e ^");
        andButton.setFont(new Font("Monospaced", Font.PLAIN, 15));
        andButton.setBounds(188, 126, 60, 35);
        andButton.addActionListener(new ButtonAction());
        contentPane.add(andButton);
        andButton.setFocusable(false);

        orButton = new JButton("\u2228");
        orButton.setToolTipText("OR: si possono utilizzare | e +");
        orButton.setFont(new Font("Monospaced", Font.PLAIN, 15));
        orButton.setBounds(313, 126, 60, 35);
        orButton.addActionListener(new ButtonAction());
        contentPane.add(orButton);
        orButton.setFocusable(false);

        implyButton = new JButton("\u2192");
        implyButton.setToolTipText("IMPLY: si può utilizzare >");
        implyButton.setFont(new Font("Monospaced", Font.PLAIN, 19));
        implyButton.setBounds(123, 192, 60, 35);
        implyButton.addActionListener(new ButtonAction());
        contentPane.add(implyButton);
        implyButton.setFocusable(false);

        biconditionalButton = new JButton("\u2194");
        biconditionalButton.setToolTipText("BICONDITIONAL: si può utilizzare =");
        biconditionalButton.setFont(new Font("Monospaced", Font.PLAIN, 19));
        biconditionalButton.setBounds(252, 192, 60, 35);
        biconditionalButton.addActionListener(new ButtonAction());
        contentPane.add(biconditionalButton);
        biconditionalButton.setFocusable(false);

        invioButton = new JButton("Invio");
        invioButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        invioButton.setBounds(353, 61, 71, 33);
        invioButton.addActionListener(new ButtonAction());
        contentPane.add(invioButton);
        invioButton.setFocusable(false);

    }

    /**
     * ActionListener per l'inserimento dei simboli logici alla pressione dei corrispettivi JButton.
     */
    private class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == notButton) {
                String text = new String();
                text = formulaText.getText() + '\u00AC';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            else if(e.getSource() == andButton) {
                String text = new String();
                text = formulaText.getText() + '\u2227';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            else if(e.getSource() == orButton) {
                String text = new String();
                text = formulaText.getText() + '\u2228';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            else if(e.getSource() == implyButton) {
                String text = new String();
                text = formulaText.getText() + "\u2192";
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            else if(e.getSource() == biconditionalButton) {
                String text = new String();
                text = formulaText.getText() + "\u2194";
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            //crea il frame del risultato alla pressione di invioButton
            else if(e.getSource() == invioButton) {
                try {
                    //crea il parser per la formula
                    FormulaParser fp = new FormulaParser();
                    //converte la formula a notazione infissa in postfissa
                    String formulaPost = fp.infixToRpn(formulaText.getText());
                    //genera la lista dei nomi delle proposizioni della formula
                    ArrayList<Character> fpProList = (ArrayList<Character>) fp.getProList();
                    //crea la finestra per decidere che logica utilizzare
                    LogicaGUI logicGui = new LogicaGUI(formulaText.getText(), formulaPost, fpProList);
                    //mostra la finestra
                    logicGui.setVisible(true);
                } catch(Exception f) {
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
                }
            }

        }

    }

    /**
     * KeyListener per l'inserimento dei simboli logici tramite tastiera.
     */
    private class OPListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            //NOT se si inserisce ! o -
            if(c == '\u0021' || c == '\u002D') {
                e.consume();
                String text = new String();
                text = formulaText.getText() + '\u00AC';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            //AND se si inserisce &, @, * o ^
            else if(c == '\u0026' || c == '\u0040' || c == '\u005E' || c == '\u002A') {
                e.consume();
                String text = new String();
                text = formulaText.getText() + '\u2227';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            //OR se si inserisce | o +
            else if(c == '\u007C' || c == '\u002B') {
                e.consume();
                String text = new String();
                text = formulaText.getText() + '\u2228';
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            //IMPLY se si inserisce >
            else if(c == '\u003E') {
                e.consume();
                String text = new String();
                text = formulaText.getText() + "\u2192";
                formulaText.setText(text);
                formulaText.requestFocus();
            }
            //BICONDITIONAL se si inserisce =
            else if(c == '\u003D') {
                e.consume();
                String text = new String();
                text = formulaText.getText() + "\u2194";
                formulaText.setText(text);
                formulaText.requestFocus();
            } else if(!(Character.isAlphabetic(c) || Character.isDigit(c) || c == '(' || c == ')' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_INSERT || c == KeyEvent.VK_SPACE)) {
                e.consume();
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            //usare enter sulla tastiera fa la stessa cosa di cliccare il JButton invio
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                try {
                    //crea il parser per la formula
                    FormulaParser fp = new FormulaParser();
                    //converte la formula a notazione infissa in postfissa
                    String formulaPost = fp.infixToRpn(formulaText.getText());
                    //genera la lista dei nomi delle proposizioni della formula
                    ArrayList<Character> fpProList = (ArrayList<Character>) fp.getProList();
                    //crea la finestra per decidere che logica utilizzare
                    LogicaGUI logicGui = new LogicaGUI(formulaText.getText(), formulaPost, fpProList);
                    //mostra la finestra
                    logicGui.setVisible(true);
                } catch(Exception f) {
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
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}

    }

}