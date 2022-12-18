package Classi;

import java.util.ArrayList;

/**
 * Implementazione di FormulaSolver per le proposizioni che seguono la logica di Gödel.
 */
public class FormulaSolverGodel extends FormulaSolver {

    /**
     * Crea il formula solver.
     * @param I Formula in notazione infissa.
     * @param F Formula in notazione postfissa.
     * @param PL Lista proposizioni.
     */
    public FormulaSolverGodel(String I, String F, ArrayList<Character> PL) {
        super(I, F, PL);
        //creazione array proposizioni
        Proposizione propArray[] = new Proposizione[PL.size()];
        //creazione counter per le combinazioni di valori
        double counter = PL.size()-1;
        //creazione tavola di verità
        String[][] truthTable = new String[(int) Math.pow(3,PL.size())][PL.size()+1];
        //creazione nomi colonne
        colonne = new String[PL.size()+1];
        colonne[PL.size()] = I;
        for(int i = 0; i < PL.size(); i++) {
            colonne[i] = PL.get(i).toString();
            //creazione proposizione
            Proposizione p = new ProposizioneGodel(PL.get(i), 0);
            //impostazione counter proposizione
            p.setCounter(counter);
            counter--;
            //aggiunta all'array di proposizioni
            propArray[i] = p;
        }

        for(int e = 0; e < (int) Math.pow(3,PL.size()); e++) {
            //inserimento valori proposizioni nella tavola di verità
            for(int n = 0; n < PL.size(); n++) {
                truthTable[e][n] = propArray[n].getStato();
            }
            //lettura della stringa
            for(int i = 0; i < F.length(); i++) {
                char c = F.charAt(i);
                //inserimento nello stack della proposizione corrispondente al carattere
                if(Character.isLetterOrDigit(c)) {
                    for(int j = 0; j < PL.size(); j++) {
                        if(propArray[j].getName().equals(c)) {
                            Proposizione t = propArray[j];
                            stack.push(t);
                            j = PL.size();
                        }
                    }
                    //se e' un NOT
                    //la proposizione successiva subisce l'operazione del metodo not
                    //e poi viene aggiunta allo stack
                } else if(c == '\u00AC') {
                    c = F.charAt(i+1);
                    for(int k = 0; k < PL.size(); k++) {
                        if(propArray[k].getName().equals(c)) {
                            Proposizione y = propArray[k];
                            stack.push(y.not());
                            k = PL.size();
                        }
                    }
                    i++;
                    //altrimenti e' un operatore
                    //viene eseguita l'operazione sulle due ultime proposizioni aggiunte allo stack
                    //viene salvata la proposizione risultante nello stack
                } else {
                    Proposizione p2 = stack.pop();
                    Proposizione p1 = stack.pop();
                    switch(c) {
                        //nel caso sia AND
                        case '\u2227':
                            stack.push(p1.and(p2));
                            break;
                        //nel caso sia OR
                        case '\u2228':
                            stack.push(p1.or(p2));
                            break;
                        //nel caso sia IMPLY
                        case '\u2192':
                            stack.push(p1.imply(p2));
                            break;
                        //nel caso sia BICONDITIONAL
                        case '\u2194':
                            stack.push(p1.bicond(p2));
                            break;
                    }
                }
            }
            //inserimento valore formula nella tavola di verita'
            truthTable[e][PL.size()] = stack.pop().getStato();
            //aumento counter delle proposizioni
            for(int f = 0; f < PL.size(); f++) {
                propArray[f].addCounter();
            }
        }
        TT = truthTable;
    }

}
