package smail.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Luis Alberto Cisneros Gómez
 * @author lcisneros
 *
 */
public class Conexion {

    public String URL="jdbc:postgresql://localhost:5432/sgu_bd";
    public String USER="postgres";
    public String PASS="root";
    public String CONTROLER="org.postgresql.Driver";


    public static Connection conn;

    /**
     * Instancia la conexión
     */
    public Conexion() { }
    
    public void abrir() throws SQLException{
    	if(conn==null || conn.isClosed())
        {
            try {
                Class.forName(CONTROLER).newInstance();
                conn = DriverManager.getConnection(URL,USER,PASS);
                System.out.println("Conexion correcta");
            } catch (Exception e) {
                System.out.println("Conexion no correcta: "+ e.getMessage());
            }
        }
    }


   /**
    * Ejecuta las sencias sql insert,delete y update
    * @param sql
    * @return true o false
    */
    public boolean ejecutarSQL(String sql)
    {
        try {
            Statement st=conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error en la ejecucion: "+e.getMessage());
            return false;
        }
    }

    /**
     * Permire ejecutar la sentenciua sql select
     * @param sql
     * @return ResultSet
     */
      public ResultSet consultaSQL(String sql)
    {
        try {
            Statement st=conn.createStatement();
            ResultSet res=st.executeQuery(sql);
            return res;
        } catch (Exception e) {
            System.out.println("Error en la consulta: "+e.getMessage());
            return null;
        }
    }
      
    /**
     * Cierra la conexión
     */
    public void cerrar()
    {
        if(conn!= null)
        {
            try {
                conn.close();
                System.out.println("Conexion cerrada");
            } catch (Exception e) {
                System.out.println("Error en el cierre: "+e.getMessage());
            }
        }
    }
}
