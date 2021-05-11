package ar.edu.utn.frc.dlc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = getConnection();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=DLC", "apipino", "apipino"
        );
    }

    public List<Documento> getAllDocumentos() throws SQLException {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM dbo.DOCUMENTOS");
        List<Documento> lista = new ArrayList<>();
        while (rs.next()){
            Documento doc = new Documento();
            doc.setId(rs.getLong("id"));
            doc.setTitulo(rs.getString("titulo"));
            doc.setPath(rs.getString("path"));
            lista.add(doc);
        }
        finish();
        return lista;
    }

    public int insertDocumentos(List<Documento> docs) throws SQLException {
        conn.setAutoCommit(false);
        int result = 0;
        try{
            for (Documento doc : docs){
                result += executeInsertDocumento(doc);
            }
            conn.commit();
        } catch (SQLException e){
            try{
                conn.rollback();
            } catch (SQLException ex){
                System.out.println("FALLO EL ROLLBACK");
                throw ex;
            }
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return result;
    }

    public int executeInsertDocumento(Documento doc) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(
                "INSERT INTO dbo.DOCUMENTO (titulo, path) " +
                        "VALUES (?,?);"
            );
            pstmt.setString(1, doc.getTitulo());
            pstmt.setString(2, doc.getPath());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("fallo el sql");
            throw e;
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
        return result;
    }

//    public int updateDocumento(Documento doc) throws SQLException {
//        stmt = conn.createStatement();
//        int result = stmt.executeUpdate(
//                "UPDATE dbo.DOCUMENTO " +
//                        "SET titulo= '"+ doc.getTitulo() +"', path= '"+ doc.getPath() +"' " +
//                        "WHERE id = "+ doc.getId()
//        );
//        finish();
//        return result;
//    }

    public List<Posteo> getAllPosteos() throws SQLException {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM dbo.POSTEOS");
        List<Posteo> lista = new ArrayList<>();
        while (rs.next()){
            Posteo post = new Posteo();
            post.setId(rs.getLong("id"));
            post.setDocumentoId(rs.getLong("documento_id"));
            post.setVocabularioId(rs.getLong("vocabulario_id"));
            post.setCantidadOcurrencias(rs.getLong("cantidad_ocurrencias"));
            lista.add(post);
        }
        finish();
        return lista;
    }

    public int insertPosteos(List<Posteo> posts) throws SQLException {
        conn.setAutoCommit(false);
        int result = 0;
        try{
            for (Posteo post : posts){
                result += executeInsertPosteo(post);
            }
            conn.commit();
        } catch (SQLException e){
            try{
                conn.rollback();
            } catch (SQLException ex){
                System.out.println("FALLO EL ROLLBACK");
                throw ex;
            }
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return result;
    }

    private int executeInsertPosteo(Posteo post) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement("INSERT INTO dbo.POSTEOS (documento_id, vocabulario_id, cantidad_ocurrencias) " +
                "VALUES (?,?,?);");
            pstmt.setLong(1, post.getDocumentoId());
            pstmt.setLong(2, post.getVocabularioId());
            pstmt.setLong(3, post.getCantidadOcurrencias());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("fallo el sql");
            throw e;
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
        return result;
    }

    public int updatePosteos(List<Posteo> posts) throws SQLException {
        conn.setAutoCommit(false);
        int result = 0;
        try{
            for (Posteo post : posts){
                result += executeUpdatePosteo(post);
            }
            conn.commit();
        } catch (SQLException e){
            try{
                conn.rollback();
            } catch (SQLException ex){
                System.out.println("FALLO EL ROLLBACK");
                throw ex;
            }
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return result;
    }

    private int executeUpdatePosteo(Posteo post) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(
                "UPDATE dbo.POSTEOS "+
                        "SET documento_id= ? , vocabulario_id= ? , cantidad_ocurrencias= ? "+
                        "WHERE id = ? "
            );
            pstmt.setLong(1, post.getDocumentoId());
            pstmt.setLong(2, post.getVocabularioId());
            pstmt.setLong(3, post.getCantidadOcurrencias());
            pstmt.setLong(4, post.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("fallo el sql");
            throw e;
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
        return result;
    }

    public List<Vocabulario> getAllVocabularios() throws SQLException {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM dbo.VOCABULARIO");
        List<Vocabulario> lista = new ArrayList<>();
        while (rs.next()){
            Vocabulario voc = new Vocabulario();
            voc.setId(rs.getLong("id"));
            voc.setPalabra(rs.getString("palabra"));
            voc.setMaximoOcurrencias(rs.getLong("maximo_ocurrencias"));
            voc.setCantidadDocumentos(rs.getLong("cantidad_documentos"));
            lista.add(voc);
        }
        finish();
        return lista;
    }

    public int insertVocabularios(List<Vocabulario> vocs) throws SQLException {
        conn.setAutoCommit(false);
        int result = 0;
        try{
            for (Vocabulario voc : vocs){
                result += executeInsertVocabulario(voc);
            }
            conn.commit();
        } catch (SQLException e){
            try{
                conn.rollback();
            } catch (SQLException ex){
                System.out.println("FALLO EL ROLLBACK");
                throw ex;
            }
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return result;
    }

    private int executeInsertVocabulario(Vocabulario voc) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement("INSERT INTO dbo.VOCABULARIO (palabra, maximo_ocurrencias, cantidad_documentos) " +
                    "VALUES (?,?,?);");
            pstmt.setString(1, voc.getPalabra());
            pstmt.setLong(2, voc.getMaximoOcurrencias());
            pstmt.setLong(3, voc.getCantidadDocumentos());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("fallo el sql");
            throw e;
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
        return result;
    }


    public int updateVocabularios(List<Vocabulario> vocs) throws SQLException {
        conn.setAutoCommit(false);
        int result = 0;
        try{
            for (Vocabulario voc : vocs){
                result += executeUpdateVocabulario(voc);
            }
            conn.commit();
        } catch (SQLException e){
            try{
                conn.rollback();
            } catch (SQLException ex){
                System.out.println("FALLO EL ROLLBACK");
                throw ex;
            }
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return result;
    }

    private int executeUpdateVocabulario(Vocabulario voc) throws SQLException {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement("UPDATE dbo.VOCABULARIO " +
                    "SET palabra= ? , maximo_ocurrencias= ? , cantidad_documentos= ? " +
                    "WHERE id = ?");
            pstmt.setString(1, voc.getPalabra());
            pstmt.setLong(2, voc.getMaximoOcurrencias());
            pstmt.setLong(3, voc.getCantidadDocumentos());
            pstmt.setLong(4, voc.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("fallo el sql");
            throw e;
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
        return result;
    }

    private void finish() throws SQLException {
        if (rs != null && !rs.isClosed()){
            rs.close();
        }
        if(stmt != null && !stmt.isClosed()){
            stmt.close();
        }
    }

    public void close() throws SQLException {
        if (rs != null && !rs.isClosed()){
            rs.close();
        }
        if(stmt != null && !stmt.isClosed()){
            stmt.close();
        }
        if (conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
