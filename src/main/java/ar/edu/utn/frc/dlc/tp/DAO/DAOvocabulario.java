package ar.edu.utn.frc.dlc.tp.DAO;

import ar.edu.utn.frc.dlc.tp.modelos.Posteo;
import ar.edu.utn.frc.dlc.tp.modelos.Termino;

import java.sql.*;

public class DAOvocabulario extends DAOcommon{

    public static int getPalabraID(String palabra)
    {
        int id = 0;
        Connection conn = getConnection();
        if(conn == null) return id;

        String query = "SELECT id " +
                        "FROM Vocabulario " +
                        "WHERE termino = ?";

        try(PreparedStatement getId = conn.prepareStatement(query);)
        {
            getId.setString(1, palabra);
            ResultSet rs = getId.executeQuery();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error en el query del metodo getPalabraID()");
        }

        return id;
    }

    public static void insertarTermino(Termino termino)
    {
        Connection conn = getConnection();
        if(conn == null) return;

        String query = "INSERT INTO Vocabulario(termino, nr, maxtf) " +
                    "VALUES (?, ?, ?)";
        try(PreparedStatement insert = conn.prepareStatement(query))
        {
            String palabra = termino.getValue();
            int nr = termino.getNr();
            int maxTf = termino.getMaxTermFrecuency();
            insert.setString(1, palabra);
            insert.setInt(2, nr);
            insert.setInt(3, maxTf);
            insert.executeUpdate();
            conn.commit();

        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el proceso de insertarTermino()");
            System.out.println(ex);
        }
    }

    public static void actualizarTermino(Termino termino)
    {
        Connection conn = getConnection();
        if(conn == null) return;

        String query = "UPDATE Vocabulario " +
                    "SET nr=?, maxtf=? " +
                    "WHERE id=?";

        try(PreparedStatement update = conn.prepareStatement(query))
        {
            String palabra = termino.getValue();
            int palabraId = getPalabraID(palabra);
            int nr = termino.getNr();
            int maxTf = termino.getMaxTermFrecuency();
            update.setInt(1, nr);
            update.setInt(2, maxTf);
            update.setInt(3, palabraId);
            update.executeUpdate();
            conn.commit();
        }catch (SQLException ex)
        {
            System.out.println("Error al ejecturar el query de actualizarTermino()");
        }
    }
}
