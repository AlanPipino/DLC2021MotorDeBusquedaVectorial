package ar.edu.utn.frc.dlc.tp.modelos;

import ar.edu.utn.frc.dlc.tp.DAO.DAOposteos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Termino implements Serializable
{
    private final String value;
    //numero de documentos en los que el termino aparecio
    private transient int nr;
    //la maxima frecuencia de aparicion del termino en los documentos del posteo documento
    private transient int maxTermFrecuency;
    private Posteo posteoActual;
    private Documento documentoAnterior;

    public Termino(String value)
    {
        this(value, 1, 1);
    }

    public Termino(String value, int nr, int maxTermFrecuency)
    {
        this.value = value;
        this.nr = nr;
        this.maxTermFrecuency = maxTermFrecuency;
        this.posteoActual = null;
    }

    public String getValue(){ return value; }
    public int getNr(){ return nr; }
    public int getMaxTermFrecuency(){ return maxTermFrecuency; }

    public ArrayList<Posteo> getPosteos(){
        return DAOposteos.getPosteos(this.value);
    }

    public Posteo getPosteo(Documento documento)
    {
        if(documento.equals(documentoAnterior)) return posteoActual;

        documentoAnterior = documento;
        return DAOposteos.getPosteo(documento, this.value);

    }

    public void flushPosteo()
    {
        DAOposteos.actualizarPosteo(posteoActual, this.value);
        posteoActual = null;
    }

    public void agregarPosteo(Documento documento)
    {
        agregarPosteo(new Posteo(documento));
    }

    public void agregarPosteo(Posteo posteo)
    {
        posteoActual =posteo;
        DAOposteos.insertarPosteo(posteo, this.value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termino termino = (Termino) o;
        return Objects.equals(value, termino.value);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.value);
        return hash;
    }

    public void actualizarTermino(Documento documento)
    {
        if(posteoActual == null)
        {
            posteoActual = new Posteo(documento);
            agregarPosteo(posteoActual);
            //this.nr = DAOposteos.getCantidadPosteos(this.value);
            this.nr++;
            return;
        }

        posteoActual.incrementarFrecuencia();

        if(posteoActual.getTermFrecuency() > maxTermFrecuency && posteoActual.getDocumento().equals(documento))
        {
            maxTermFrecuency = posteoActual.getTermFrecuency();
        }
    }

    @Override
    public String toString()
    {
        ArrayList<Posteo> posteos = getPosteos();
        StringBuilder sb = new StringBuilder("Termino:" + value);
        sb.append(" [nr="+ nr + " max tf="+maxTermFrecuency+"] ");
        sb.append(" Posteo: ");
        for(Posteo posteo : posteos)
        {
            sb.append(posteo.toString());
            sb.append(" ");
        }
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
