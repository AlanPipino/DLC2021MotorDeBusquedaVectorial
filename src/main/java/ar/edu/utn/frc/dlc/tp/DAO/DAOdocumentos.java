package utn.dlc.tp.DAO;

import utn.dlc.tp.modelos.Documento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOdocumentos extends DAOcommon{

    public static void insertarDocumento(Documento documento)
    {
        Connection conn = getConnection();
        if(conn == null) return;
        String query = "INSERT INTO Documentos(id, path) " +
                "VALUES (?, ?)";

        try(PreparedStatement insert = conn.prepareStatement(query))
        {
            int id = documento.getId();
            String path = documento.getPath();
            insert.setInt(1, id);
            insert.setString(2, path);
            insert.executeUpdate();
            conn.commit();
        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el query de insertarDocumento()");
        }
    }

    public static ArrayList<Documento> getDocumentos()
    {
        ArrayList<Documento> documentos = new ArrayList<>();
        Statement statement = getStatement();
        try{
            assert statement != null;
            ResultSet rs = statement.executeQuery("SELECT id, path FROM Documentos");
            while(rs.next())
            {
                int id = rs.getInt(1);
                String path = rs.getString(2);
                Documento documento = new Documento(id, path);
                documentos.add(documento);
            }

        }catch (SQLException ex)
        {
            System.out.println("Error al ejecutar el query de getDocumentos()");
        }
        return documentos;
    }

    public static void insertarDocumentos(List<Documento> documentos)
    {
        for(Documento documento: documentos)
        {
            insertarDocumento(documento);
        }
    }
}
