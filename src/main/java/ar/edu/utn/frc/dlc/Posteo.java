package ar.edu.utn.frc.dlc;

public class Posteo {
    private Long id;
    private Long documentoId;
    private Long vocabularioId;
    private Long cantidadOcurrencias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }

    public Long getVocabularioId() {
        return vocabularioId;
    }

    public void setVocabularioId(Long vocabularioId) {
        this.vocabularioId = vocabularioId;
    }

    public Long getCantidadOcurrencias() {
        return cantidadOcurrencias;
    }

    public void setCantidadOcurrencias(Long cantidadOcurrencias) {
        this.cantidadOcurrencias = cantidadOcurrencias;
    }
}
