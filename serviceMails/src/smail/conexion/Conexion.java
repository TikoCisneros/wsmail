package smail.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    private String URL="jdbc:postgresql://localhost:5432/sisdron";
    private String USER="postgres";
    private String PASS="root";
    private String CONTROLER="org.postgresql.Driver";

    public static Connection conn;

    /**
     * Instancia la conexi�n
     */
    public Conexion()
    {
        if(conn==null)
        {
            try {
                Class.forName(CONTROLER).newInstance();
                conn = DriverManager.getConnection(URL,USER,PASS);
                System.out.println("Conexion correcta");
            } catch (Exception e) {
                System.out.println("Conexion incorrecta: "+ e.getMessage());
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
     * Cierra la conexi�n
     */
    public void cerrar()
    {
        if(conn!= null)
        {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error en el cierre: "+e.getMessage());
            }
        }
    }
}
