package ar.edu.utn.frc.dlc;

public class Vocabulario {
    private Long id;
    private String palabra;
    private Long maximoOcurrencias;
    private Long cantidadDocumentos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Long getMaximoOcurrencias() {
        return maximoOcurrencias;
    }

    public void setMaximoOcurrencias(Long maximoOcurrencias) {
        this.maximoOcurrencias = maximoOcurrencias;
    }

    public Long getCantidadDocumentos() {
        return cantidadDocumentos;
    }

    public void setCantidadDocumentos(Long cantidadDocumentos) {
        this.cantidadDocumentos = cantidadDocumentos;
    }
}
