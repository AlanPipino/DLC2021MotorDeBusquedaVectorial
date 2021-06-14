package ar.edu.utn.frc.dlc.tp.modelos;

import java.io.Serializable;
import java.util.Objects;

public class Documento implements Serializable
{
    int id;
    String path;

    public Documento(int id, String path)
    {
        this.id = id;
        this.path = path;
    }
    public int getId(){ return id; }
    public String getPath(){
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return id == documento.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(id);

        return hash;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
