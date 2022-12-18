package Classi;

import java.math.*;

/**
 * La classe astratta rappresenta una proposizione logica a tre valori.
 * <p>
 * Le sue sottoclassi implementano le varie operazioni a seconda della logica seguita (Kleene, Łukasiewicz e Gödel).
 * <p>
 * Comprende metodi per le operazioni NOT, AND, OR, IMPLY, e BICONDITIONAL,
 * e un counter per la creazione della tavola di verità di una formula logica.
 */
public abstract class Proposizione {

    protected Boolean FALSE = false;
    protected Boolean TRUE = false;
    protected Boolean UNDEF = false;
    protected Character name = '*';
    protected double counter = 1;
    protected double maxCounter = 1;

    /**
     * Il costruttore per le proposizioni senza nome create dalle operazioni.
     * @param state Lo stato della proposizione.
     */
    public Proposizione(int state) {
        if(state == 0) {
            this.FALSE = true;
        } else if(state == 1) {
            this.TRUE = true;
        } else if(state == -1) {
            this.UNDEF = true;
        }

    }

    /**
     * Costruttore per le proposizioni inserite dall'utente.
     * @param name Il nome della proposizione.
     * @param state Lo stato di partenza della proposizione.
     */
    public Proposizione(Character name, int state) {
        this.name = name;
        if(state == 0) {
            this.FALSE = true;
        } else if(state == 1) {
            this.TRUE = true;
        } else if(state == -1) {
            this.UNDEF = true;
        }

    }

    /**
     * Restituisce il nome dell'oggetto proposizione.
     * @return Il carattere nome della proposizione.
     */
    public Character getName() {
        return this.name;
    }

    /**
     * Restituisce lo stato dell'oggetto proposizione.
     * @return Una stringa che rappresenta lo stato della proposizione.
     */
    public String getStato() {
        if(this.FALSE) {
            return "F";
        } else if(this.TRUE) {
            return "T";
        } else if(this.UNDEF) {
            return "U";
        }
        return "NULL";
    }

    /**
     * L'operazione NOT.
     */
    public abstract Proposizione not();

    /**
     * L'operazione AND.
     */
    public abstract Proposizione and(Proposizione p);

    /**
     * L'operazione OR.
     */
    public abstract Proposizione or(Proposizione p);

    /**
     * L'operazione IMPLY.
     */
    public abstract Proposizione imply(Proposizione p);

    /**
     * L'operazione BICONDITIONAL.
     */
    public abstract Proposizione bicond(Proposizione p);

    /**
     * Imposta il maxCounter della proposizione che servirà alla creazione
     * di tutte le possibili combinazioni di valori nella tavola di verità.
     * @param i La posizione della proposizione nella tavola di verità da destra a sinistra.
     */
    public void setCounter(double i) {
        if(i > 0)
            maxCounter = Math.pow(3, i);
    }

    /**
     * Incrementa di 1 il counter.
     * Se è maggiore o uguale al maxCounter, la proposizione passa al valore successivo e il counter viene resettato.
     */
    public void addCounter() {
        counter++;
        if(counter > maxCounter) {
            counter = 1;
            if(this.FALSE) {
                this.FALSE = false;
                this.TRUE = true;
            } else if(this.TRUE) {
                this.TRUE = false;
                this.UNDEF = true;
            } else if(this.UNDEF) {
                this.UNDEF = false;
                this.FALSE = true;
            }
        }
    }

}
