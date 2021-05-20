package utn.dlc.tp.DAO;

import utn.dlc.tp.modelos.Documento;
import utn.dlc.tp.modelos.Posteo;

import java.sql.*;
import java.util.ArrayList;

public class DAOposteos extends DAOcommon
{
    public static Posteo getPosteo(Documento documento, String palabra)
    {
        Posteo posteo = null;
        Connection conn = getConnection();
        if(conn == null) return null;

        String query = "SELECT idDocumento, tf FROM  Posteos " +
                        "WHERE idDocumento=? AND idPalabra=?";
        try(PreparedStatement get = conn.prepareStatement(query))
        {
            int idDoc = documento.getId();
            int idPalabra = DAOvocabulario.getPalabraID(palabra);
            get.setInt(1, idDoc);
            get.setInt(2, idPalabra);
            ResultSet rs = get.executeQuery();

            if(rs.next())
            {
                int tf = rs.getInt(2);
                posteo = new Posteo(documento, tf);
            }

        }catch (SQLException ex)
        {
            System.out.println("Error al ejecutar el query de getPosteo()");
        }
        return posteo;
    }

    public static ArrayList<Posteo> getPosteos(String palabra)
    {
        ArrayList<Posteo> posteos = new ArrayList<>();
        Connection conn = getConnection();
        if(conn == null) return posteos;

        String query = "SELECT p.tf, d.id, d.path " +
                    "FROM Posteos p join Documentos d on d.id = p.idDocumento " +
                    "WHERE p.idPalabra = ?";
        try(PreparedStatement get = conn.prepareStatement(query))
        {
            int idPalabra = DAOvocabulario.getPalabraID(palabra);
            get.setInt(1, idPalabra);

            ResultSet rs = get.executeQuery();
            while(rs.next())
            {
                int tf = rs.getInt(1);
                int docId = rs.getInt(2);
                String docPath = rs.getString(3);

                Documento documento = new Documento(docId, docPath);
                Posteo posteo = new Posteo(documento, tf);
                posteos.add(posteo);
            }

        }catch (SQLException ex)
        {
            System.out.println("Error al ejecutar el query de getPosteos()");
        }
        return posteos;
    }

    public static void insertarPosteo(Posteo posteo, String palabra)
    {
        Connection conn = getConnection();
        if(conn == null || posteo == null) return;

        String query ="INSERT INTO Posteos(idDocumento, idPalabra, tf) " +
                    "VALUES (?, ?, ?)";
        try(PreparedStatement insert = conn.prepareStatement(query))
        {
            int palabraId = DAOvocabulario.getPalabraID(palabra);
            int docId = posteo.getDocumento().getId();
            int tf = posteo.getTermFrecuency();
            insert.setInt(1, docId);
            insert.setInt(2, palabraId);
            insert.setInt(3, tf);
            insert.executeUpdate();
            conn.commit();
        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el query de insertarPosteo()");
        }
    }

    public static void actualizarPosteo(Posteo posteo, String palabra)
    {
        Connection conn = getConnection();
        if(conn == null || posteo == null) return;

        String query = "UPDATE Posteos " +
                    "SET tf=? " +
                    "WHERE idPalabra=? AND idDocumento=?";
        try(PreparedStatement update = conn.prepareStatement(query))
        {
            int palabraId = DAOvocabulario.getPalabraID(palabra);
            int docId = posteo.getDocumento().getId();
            int tf = posteo.getTermFrecuency();
            update.setInt(1, tf);
            update.setInt(2, palabraId);
            update.setInt(3, docId);
            update.executeUpdate();
            conn.commit();

        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el query de actualizarPosteo()");
        }
    }

    public static int getCantidadPosteos(String palabra)
    {
        int cantidad = 0;
        Connection conn = getConnection();
        if(conn == null) return cantidad;

        String query = "SELECT COUNT(*) FROM Posteos WHERE idPalabra=?";
        try(PreparedStatement get = conn.prepareStatement(query))
        {
            int palabraId = DAOvocabulario.getPalabraID(palabra);
            get.setInt(1, palabraId);
            ResultSet rs = get.executeQuery();
            if(rs.next())
            {
                cantidad = rs.getInt(1);
            }
        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el query de getCantidadPosteos()");
        }
        return cantidad;
    }
}
