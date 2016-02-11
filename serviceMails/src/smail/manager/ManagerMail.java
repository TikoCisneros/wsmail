package smail.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import smail.conexion.SingletonJDBC;
import smail.entities.Email;
import smail.entities.WsMailYachay;
import smail.structure.Mail;
import smail.structure.Mailer;

public class ManagerMail {
	
	private SingletonJDBC conn;
	
	public ManagerMail() {
		conn = SingletonJDBC.getInstance();
	}
	
	/**
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void envioMail(JSONObject data) throws Exception{
		if(validarEstructura(data)){
			WsMailYachay serverMail = existeMailActivo(data.get("id").toString());
			if(serverMail==null)
				throw new Exception("No existe el usuario de correo enviado.");
			else
				//Mailer.generateAndSendEmail(crearCorreo(data));
				Mail.generateAndSendEmail(crearCorreo(data), serverMail);
		}else
			throw new Exception("Estructura inválida.");
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	private boolean validarEstructura(JSONObject data) {
		if(data.get("id")==null || data.get("para")==null ||
			data.get("asunto")==null || data.get("body")==null ||
			data.get("id").toString().trim().isEmpty() || data.get("para").toString().trim().isEmpty() ||
			data.get("asunto").toString().trim().isEmpty() || data.get("body").toString().trim().isEmpty() ||
			data.get("para").toString().contains(";"))
			return false;
		else if((data.get("cc")!=null && data.get("cc").toString().trim().isEmpty() && data.get("cc").toString().contains(";")) ||
				(data.get("cco")!=null && data.get("cco").toString().trim().isEmpty() && data.get("cco").toString().contains(";")))
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public WsMailYachay existeMailActivo(String id) throws SQLException{
		ResultSet consulta = conn.consultaSQL("SELECT * FROM ws_mail_yachay WHERE mai_id='"+id+"' AND mai_estado='A'");
		if(consulta!=null){
			consulta.next();
			return new WsMailYachay(consulta.getString("mai_server"), 
					consulta.getString("mai_usuario"), consulta.getString("mail_pwd_resp"));
		}else
			return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private Email crearCorreo(JSONObject data) {
		Email estructura = new Email();
		estructura.setPara(data.get("para").toString());
		if(data.get("cc")!=null)
			estructura.setCc(data.get("cc").toString());
		if(data.get("cco")!=null)
			estructura.setCco(data.get("cco").toString());
		estructura.setAsunto(data.get("asunto").toString());
		estructura.setBody(data.get("body").toString());
		return estructura;
	}

}
