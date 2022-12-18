package Classi;

/**
 * La classe segue le operazioni logiche della "forte logica di indeterminazione" di Stephen Cole Kleene
 * e della "logica del paradosso" di Graham Priest.
 */
public class ProposizioneKleene extends Proposizione {

    public ProposizioneKleene(int state) {
        super(state);
    }

    public ProposizioneKleene(Character name, int state) {
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
            return new ProposizioneKleene(1);
        } else if(this.TRUE) {
            return new ProposizioneKleene(0);
        } else if(this.UNDEF) {
            return new ProposizioneKleene(-1);
        }

        return new ProposizioneKleene(-1);
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
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(0);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(0);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(-1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }
        }

        return new ProposizioneKleene(-1);
    }

    /**
     * L'operazione OR:
     * <p>
     * F∨F = F | F∨T = T | F∨U = U
     * <p>
     * T∨F = T | T∨T = T | T∨U = T
     * <p>
     * U∨F = U | U∨T = T | U∨U = U
     * <p>
     * È un'operazione commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione or(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneKleene(1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneKleene(-1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }
        }

        return new ProposizioneKleene(-1);
    }

    /**
     * L'operazione IMPLY:
     * <p>
     * F→F = T | F→T = T | F→U = T
     * <p>
     * T→F = F | T→T = T | T→U = U
     * <p>
     * U→F = U | U→T = T | U→U = U
     * <p>
     * È un'operazione non commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione imply(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneKleene(1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneKleene(-1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }
        }
        return new ProposizioneKleene(-1);
    }

    /**
     * L'operazione BICONDITIONAL:
     * <p>
     * F↔F = T | F↔T = F | F↔U = U
     * <p>
     * T↔F = F | T↔T = T | T↔U = U
     * <p>
     * U↔F = U | U↔T = U | U↔U = U
     * <p>
     * È un'operazione commutativa.
     * @param p La seconda proposizione.
     * @return Una nuova proposizione senza nome.
     */
    public Proposizione bicond(Proposizione p) {
        if(this.FALSE) {
            if(p.FALSE) {
                return new ProposizioneKleene(1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(0);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }

        } else if(this.TRUE) {
            if(p.FALSE) {
                return new ProposizioneKleene(0);
            } else if(p.TRUE) {
                return new ProposizioneKleene(1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }

        } else if(this.UNDEF) {
            if(p.FALSE) {
                return new ProposizioneKleene(-1);
            } else if(p.TRUE) {
                return new ProposizioneKleene(-1);
            } else if(p.UNDEF) {
                return new ProposizioneKleene(-1);
            }
        }
        return new ProposizioneKleene(-1);
    }

}
