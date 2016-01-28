package smail.webservice;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import smail.manager.ManagerMail;

/**
 * Servlet implementation class WSMail
 */
@WebServlet("/WSMailYachay")
public class WSMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String URL_WS = "/WSMailYachay";
    private ManagerMail mng;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WSMail() {
        super();
        mng = new ManagerMail();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestPOST(request, response);
	}
    
    /**
     * Método para procesar las llamadas post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequestPOST(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		addCorsHeader(response);
		JSONObject data = getBodyData(request);
		// switch para cada caso de servicos
		String path = request.getServletPath();
		if (path.equalsIgnoreCase(URL_WS))
			sendEmail(request, response, data);
    }

	/**
     * Añade las cabeceras para que las aplicaciones puedan acceder a los recursos web
     * @param response
     */
    private void addCorsHeader(HttpServletResponse response) { 	
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept");
		response.setContentType ("text/html;charset=utf-8");
	}
    
    /**
	 * Transforma objeto JSON para leerlo
	 * @param request
	 * @return
	 */
	private JSONObject getBodyData(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		JSONObject o = null;
		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			JSONParser parser = new JSONParser();
			System.out.println(sb.toString());
			o = (JSONObject) parser.parse(sb.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	/**
	 * Método para el envío de mails
	 * @param request
	 * @param response
	 * @param data
	 * @throws ServletException
	 * @throws IOException
	 */
    private void sendEmail(HttpServletRequest request,
    		HttpServletResponse response, JSONObject data) throws ServletException, IOException {
		try {
			mng.envioMail(data);
			response.getWriter().write(jsonMensajes("OK", "", "Mensaje enviado correctamente"));
		} catch (Exception e ) {
			e.printStackTrace();
			response.getWriter().write(jsonMensajes("ERROR", "", e.getMessage()));
		} finally {
			response.getWriter().close();
		}	
	}
    
    /**
	 * Devuelve la respuesta del servicio
	 * 
	 * @param status
	 * @param value
	 * @param mensaje
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String jsonMensajes(String status, Object value, String mensaje) {
		JSONObject obj = new JSONObject();
		obj.put("status", status);
		obj.put("value", value);
		obj.put("mensaje", mensaje);
		return obj.toJSONString();
	}

}
