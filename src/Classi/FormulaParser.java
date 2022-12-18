package Classi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.lang.Character;

/**
 * La classe riceve in input la stringa a notazione infissa (cioe' con gli operatori in mezzo agli operandi)
 * fornita dall'utente, e tramite l'algoritmo Shunting Yard ne esegue il parsing, salvando le proposizioni in una lista,
 * e producendo una stringa a notazione suffissa, conosciuta anche come notazione polacca inversa.
 */
public class FormulaParser {

    //la lista per salvare i nomi delle proposizioni
    private ArrayList<Character> prolist = new ArrayList<Character>();

    /**
     * Restituisce la lista dei nomi delle proposizioni creata dalla classe durante il parsing.
     * @return La lista dei nomi delle proposizioni.
     */
    public List<Character> getProList() {
        return prolist;
    }

    /**
     * Controlla se il carattere analizzato e' una lettera o numero.
     * @param c Il carattere analizzato.
     * @return true se e' una lettera o numero, false altrimenti.
     */
    private static boolean isProposizione(char c) {
        if (Character.isLetterOrDigit(c)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Da la priorita' agli operatori logici.
     * <p>
     * AND > OR > IMPLY > BICONDITIONAL
     * @param ch L'operatore a cui dare la priorita'.
     * @return La priorita' dell'operatore. -1 se non e' un operatore.
     */
    private static int getPrecedenza(char ch) {
        //AND
        if (ch == '\u2227')
            return 4;
            //OR
        else if (ch == '\u2228')
            return 3;
            //IMPLY
        else if (ch == '\u2192')
            return 2;
            //BICONDITIONAL
        else if(ch == '\u2194')
            return 1;
        else
            return -1;
    }

    /**
     * Prende come argomento una stringa in notazione infissa per convertirla in notazione postfissa.
     * @param formula La stringa della formula logica da convertire.
     * @return Una stringa della stessa formula ma in notazione postfissa.
     * <p>
     * Una stringa di errore se la formula contiene errori di battitura.
     */
    public String infixToRpn(String formula) {
        //lo stack per operatori e parentesi
        Stack<Character> opstack = new Stack<>();
        //la stringa di output che conterra' la formula in notazione postfissa
        String output = new String("");
        //ciclo for per analizzare la stringa in input
        for (int i = 0; i < formula.length(); ++i) {
            char c = formula.charAt(i);
            //se il carattere in posizione i e' una proposizione
            //viene messo direttamente nella stringa di output e,
            //se non gia' inserito nella lista di proposizioni,
            //aggiunto a quest'ultima
            if (Character.isLetterOrDigit(c)) {
                output += c;
                if (!prolist.contains(c)) {
                    prolist.add(c);
                }
                //se e' un NOT viene messo direttamente nella stringa di output
            } else if(c == '\u00AC') {
                output += c;
            }
            //se e' uno spazio vuoto viene ignorato
            else if(c == ' ') {}
            //se il carattere in posizione i e' una parentesi aperta '(',
            //viene inserita nello stack di operatori
            else if (c == '(') {
                opstack.push(c);
            }
            //se il carattere in posizione i e' una parentesi chiusa ')',
            //si fa il pop degli operatori dallo stack e si aggiungono
            //alla stringa di output finchè non si trova una
            //parentesi aperta '('
            else if (c == ')') {
                while (!opstack.isEmpty() && opstack.peek() != '(') {
                    output += opstack.pop();
                }
                opstack.pop();
            }

            //se il carattere in posizione i e' un operatore con priorita' minore o uguale al più alto nello stack,
            //si fa il pop finche' non se ne incontra uno con priorita' minore,
            //aggiungendoli mano a mano alla stringa di output
            else {
                while (!opstack.isEmpty() && getPrecedenza(c) <= getPrecedenza(opstack.peek())) {
                    output += opstack.pop();
                }
                opstack.push(c);
            }
        }

        //pop degli operatori rimasti, inserendoli nella stringa di output
        //se si trova una parentesi aperta, vuol dire che e' stata inserita una formula non valida
        while (!opstack.isEmpty()) {
            if (opstack.peek() == '(')
                return "La formula non è valida.";
            output += opstack.pop();
        }
        return output;
    }
}