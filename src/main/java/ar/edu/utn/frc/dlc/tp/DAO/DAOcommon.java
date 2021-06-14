package ar.edu.utn.frc.dlc.tp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAOcommon
{

    protected static Connection getConnection()
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException ex)
        {
            System.out.println("No se encontro el driver");
            return null;
        }

        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;database=DLCtp", "sa", "root");
            return conn;
        }catch (SQLException ex)
        {
            System.out.println("No se pudo realizar la conexion a la base de datos");
            return null;
        }
    }

    protected static Statement getStatement()
    {
            try
            {
                Connection conn = getConnection();
                return conn.createStatement();
            }catch (SQLException ex)
            {
                System.out.println("Error al crear el statement");
                return null;
            }

    }
}
