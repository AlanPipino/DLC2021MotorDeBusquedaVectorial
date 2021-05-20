package utn.dlc.tp.procesos;

import utn.dlc.tp.DAO.DAOdocumentos;
import utn.dlc.tp.modelos.Documento;
import utn.dlc.tp.modelos.Termino;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args)
    {

        Indexador indexador = new Indexador(false);
        ArrayList<Documento> documentos = DAOdocumentos.getDocumentos();
        indexador.indexarDocumento(documentos.get(0));
        indexador.indexarDocumento(documentos.get(1));
        indexador.indexarDocumento(documentos.get(2));

//        indexador.indexarDocumentos(documentos);

//        Documento d1 = new Documento(1, "d1.txt");
//        indexador.indexarDocumento(d1);
//
//        Documento d2 = new Documento(2, "d2.txt");
//        indexador.indexarDocumento(d2);
//
//        Documento d3 = new Documento(3, "d3.txt");
//        indexador.indexarDocumento(d3);
//
//        Documento d4 = new Documento(4, "d4.txt");
//        indexador.indexarDocumento(d4);
//
//        Documento d5 = new Documento(5, "d5.txt");
//        indexador.indexarDocumento(d5);

    }
}
