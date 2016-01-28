package smail.entities;

public class WsMailYachay {
	private String maiServer;
	private String maiUsuario;
	private String maiPwd;
	
	public WsMailYachay() {
	}
	
	public WsMailYachay(String maiServer, String maiUsuario,
			String maiPwd) {
		this.maiServer = maiServer;
		this.maiUsuario = maiUsuario;
		this.maiPwd = maiPwd;
	}

	/**
	 * @return the maiServer
	 */
	public String getMaiServer() {
		return maiServer;
	}

	/**
	 * @param maiServer the maiServer to set
	 */
	public void setMaiServer(String maiServer) {
		this.maiServer = maiServer;
	}

	/**
	 * @return the maiUsuario
	 */
	public String getMaiUsuario() {
		return maiUsuario;
	}

	/**
	 * @param maiUsuario the maiUsuario to set
	 */
	public void setMaiUsuario(String maiUsuario) {
		this.maiUsuario = maiUsuario;
	}

	/**
	 * @return the maiPwd
	 */
	public String getMaiPwd() {
		return maiPwd;
	}

	/**
	 * @param maiPwd the maiPwd to set
	 */
	public void setMaiPwd(String maiPwd) {
		this.maiPwd = maiPwd;
	}
	
	
	
}
