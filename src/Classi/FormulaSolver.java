package Classi;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.*;

/**
 * La classe riceve in input la stringa della formula in notazione postfissa
 * e la lista di proposizioni creata da FormulaParser, per poi crearne la tavola di verità.
 * Usa anche la stringa della formula in notazione infissa per la tavola di verità.
 * Utilizza uno stack per i caratteri della stringa, e alla lettura di un operatore
 * esegue l'operazione opportuna sulle due proposizioni che lo precedono.
 */
public abstract class FormulaSolver {

    Stack<Proposizione> stack = new Stack<Proposizione>();
    String[][] TT;
    String[] colonne;

    /**
     * Risolve tutte le combinazioni di valori possibili della formula.
     */
    public FormulaSolver(String I, String F, ArrayList<Character> PL) {
    }

    /**
     * Restituisce la tavola di verità della formula.
     * @return L'array bidimensionale di soluzioni.
     */
    public String[][] soluzione() {
        return TT;
    }

    /**
     * Restituisce i nomi delle colonne della tavola di verità.
     * @return L'array dei nomi delle colonne.
     */
    public String[] getColonne() {
        return colonne;
    }
}
