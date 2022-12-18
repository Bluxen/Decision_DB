package Classi;

/**
 * La classe segue la logica di Jan Łukasiewicz.
 */
public class ProposizioneLuka extends Proposizione {

    public ProposizioneLuka(int state) {
        super(state);
    }

    public ProposizioneLuka(Character name, int state) {
        super(name, state);
    }

    /**
     * L'operazione NOT:
     * <p>
     * ¬F = T
     * <p>
     * ¬T = F
     * <p>
     * ¬U = U
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione not() {
        if(this.FALSE) {
            return new ProposizioneLuka(1);
        } else if(this.TRUE) {
            return new ProposizioneLuka(0);
        } else if(this.UNDEF) {
            return new ProposizioneLuka(-1);
        }

        return new ProposizioneLuka(-1);
    }

    /**
     * L'operazione AND:
     * <p>
     * F∧F = F | F∧T = F | F∧U = F
     * <p>
     * T∧F = F | T∧T = T | T∧U = U
     * <p>
     * U∧F = F | U∧T = U | U∧U = U
     * <p>
     * È un'operazione commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione and(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(0);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(0);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(-1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }
        }

        return new ProposizioneLuka(-1);
    }

    /**
     * L'operazione OR:
     * <p>
     * F∨F = F | F∨T = T | F∨U = U
     * <p>
     * T∨F = T | T∨T = T | T∨U = T
     * <p>
     * U∨F = U | U∨T = T | U∨U = T
     * <p>
     * È un'operazione commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione or(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneLuka(1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneLuka(-1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(1);
            }
        }

        return new ProposizioneLuka(-1);
    }

    /**
     * L'operazione IMPLY:
     * <p>
     * F→F = T | F→T = T | F→U = T
     * <p>
     * T→F = F | T→T = T | T→U = U
     * <p>
     * U→F = U | U→T = T | U→U = T
     * <p>
     * È un'operazione non commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione imply(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneLuka(1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneLuka(-1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(1);
            }
        }
        return new ProposizioneLuka(-1);
    }

    /**
     * L'operazione BICONDITIONAL:
     * <p>
     * F↔F = T | F↔T = F | F↔U = U
     * <p>
     * T↔F = F | T↔T = T | T↔U = U
     * <p>
     * U↔F = U | U↔T = U | U↔U = T
     * <p>
     * È un'operazione commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione bicond(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneLuka(1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(0);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneLuka(0);
            } else if(p.TRUE) {
                return new ProposizioneLuka(1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneLuka(-1);
            } else if(p.TRUE) {
                return new ProposizioneLuka(-1);
            } else if(p.UNDEF) {
                return new ProposizioneLuka(1);
            }
        }
        return new ProposizioneLuka(-1);
    }

}
