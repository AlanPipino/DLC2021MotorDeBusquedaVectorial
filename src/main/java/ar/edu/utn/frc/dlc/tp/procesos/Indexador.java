package ar.edu.utn.frc.dlc.tp.procesos;

import ar.edu.utn.frc.dlc.tp.DAO.DAOvocabulario;
import ar.edu.utn.frc.dlc.tp.modelos.Documento;
import ar.edu.utn.frc.dlc.tp.modelos.Posteo;
import ar.edu.utn.frc.dlc.tp.modelos.Termino;
import ar.edu.utn.frc.dlc.tp.modelos.Vocabulario;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Indexador {
    private final Vocabulario vocabulario;
    private boolean readStopWords;
    private ArrayList<String> stopWords;

    public Indexador()
    {
        this(true);
    }

    public Indexador(boolean readStopWords)
    {
        this.readStopWords = readStopWords;
        this.vocabulario = new Vocabulario();
        if(!readStopWords) { this.loadStopWords(); }
    }

    private void loadStopWords()
    {
        stopWords = new ArrayList<String>();
        File stopWordsFile = new File("src/main/resources/stopwords.txt");
        try(Scanner scanner = new Scanner(stopWordsFile);)
        {
            while (scanner.hasNext())
            {
                this.stopWords.add(scanner.next());
            }
        }catch (FileNotFoundException ex)
        {
            System.out.println("No se encontro el archivo de stopwords");
        }
    }
    public void indexarDocumento(Documento documento)
    {
        String path = documento.getPath();

        File file = new File(path);
        try(Scanner scanner = new Scanner(file).useDelimiter("\\W*\\s+\\W*"))
        {
            while(scanner.hasNext())
            {
                String palabra = scanner.next().toLowerCase(Locale.ROOT);

                if(stopWords.contains(palabra) && !readStopWords)continue;

                Termino termino = new Termino(palabra);
                if(!vocabulario.contains(termino))
                {
                    DAOvocabulario.insertarTermino(termino);
                    vocabulario.add(termino, documento);
                    continue;
                }
                termino = vocabulario.actualizarTermino(termino, documento);
                DAOvocabulario.actualizarTermino(termino);
            }
            this.flushPosteos();
        }catch (FileNotFoundException ex)
        {
            System.out.println("No se encontro el archivo");
        }
    }

    public void indexarDocumentos(Collection<Documento> documentos)
    {
        for(Documento documento: documentos)
        {
            indexarDocumento(documento);
        }
    }

    private void flushPosteos()
    {
        vocabulario.flushPosteos();
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Vocabulario:");
        sb.append(System.lineSeparator());
        sb.append(this.vocabulario.toString());
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
