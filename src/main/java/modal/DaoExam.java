package modal;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoExam {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    private final String INSERT_REGISTRAR = "insert into registrar(nombre, primerapellido, segundoapellido, sexo, nacimiento, fecha, curp) "
            + "values(?,?,?,?,?,?,?)";
    private final String GET_REGISTRAR = "select * from registrar";
    public boolean saveRegistro(String nombre, String primerapellido, String segundoapellido, String sexo, String nacimiento, Date fecha, String curp){
    try{
        conn = new MySQLConnection().getConnection();
        String query = INSERT_REGISTRAR;
        pstm = conn.prepareStatement(query);
        pstm.setString(1, nombre);
        pstm.setString(2, primerapellido);
        pstm.setString(3, segundoapellido);
        pstm.setString(4, sexo);
        pstm.setString(5, nacimiento);
        pstm.setDate(6, fecha);
        pstm.setString(7, curp);

        return pstm.executeUpdate()==1;
    }catch(SQLException e){
        Logger.getLogger(DaoExam.class.getName())
                .log(Level.SEVERE, "Error saveOperation ->", e);
        return false;
    }
}
    public ArrayList<BeanExam> showRegistrar (){
        ArrayList<BeanExam> registroList = new ArrayList<>();
        BeanExam registro = null;
        try{
            conn = new MySQLConnection().getConnection();
            String query = GET_REGISTRAR;
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while(rs.next()){
                registro = new BeanExam();
                registro.setNombre(rs.getString("nombre"));
                registro.setPrimerapellido(rs.getString("primer apellido"));
                registro.setSegundoapellido(rs.getString("segundo apellido"));
                registro.setSexo(rs.getString("sexo"));
                registro.setNacimiento(rs.getString("nacimiento"));
                registro.setFecha(rs.getDate("fecha"));
                registro.setCurp(rs.getString("curp"));


                registroList.add(registro);
            }
        }catch(SQLException e){
            Logger.getLogger(DaoExam.class.getName())
                    .log(Level.SEVERE, "Error en showOperations -> ", e);
        }finally{
            closeConnections();
        }
        return registroList;
    }

    public void  closeConnections(){
        try{
            if(conn != null){
                conn.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

