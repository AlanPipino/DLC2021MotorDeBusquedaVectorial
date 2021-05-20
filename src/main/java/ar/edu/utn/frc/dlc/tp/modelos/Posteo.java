package utn.dlc.tp.modelos;

import java.io.Serializable;
import java.util.Objects;

public class Posteo implements Serializable
{
    Documento documento;
    int termFrecuency;

    public Posteo(Documento documento){
        this(documento, 1);
    }

    public Posteo(Documento documento, int termFrecuency){
        this.documento = documento;
        this.termFrecuency = termFrecuency;
    }

    public void incrementarFrecuencia(){
        termFrecuency++;
    }

    public Documento getDocumento(){
        return documento;
    }

    public int getTermFrecuency(){
        return termFrecuency;
    }

    @Override
    public String toString()
    {
        return "d" + documento.getId() + " (tf=" + termFrecuency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posteo posteo = (Posteo) o;
        return Objects.equals(documento, posteo.documento);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(documento);

        return hash;
    }
}

