package ar.edu.utn.frc.dlc.tp.modelos;

import java.util.*;

public class Vocabulario {

    Hashtable<Integer, Termino> terminos;

    public Vocabulario(){
        this.terminos = new Hashtable<>();
    }

    public boolean contains(Termino termino)
    {
        return terminos.contains(termino);
    }

    public void add(Termino termino, Documento documento){
        termino.agregarPosteo(documento);
        terminos.put(termino.hashCode(), termino);

    }

    public Termino actualizarTermino(Termino termino, Documento documento)
    {
        Termino original = terminos.get(termino.hashCode());
        original.actualizarTermino(documento);
        return original;
    }

    public ArrayList<Posteo> getPosteos()
    {
        ArrayList<Posteo> posteos = new ArrayList<>();
        for(Termino termino: terminos.values())
        {
            posteos.addAll(termino.getPosteos());
        }

        return posteos;
    }

    public Hashtable<Integer, Termino> getTerminos()
    {
        return terminos;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("{");
        sb.append(System.lineSeparator());
        for(Termino termino : terminos.values())
        {
            sb.append(termino.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void flushPosteos()
    {
        for(Termino termino: terminos.values())
        {
            termino.flushPosteo();
        }
    }
}
