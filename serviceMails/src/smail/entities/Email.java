package smail.entities;


public class Email {
	
	private String para;
	private String cc;
	private String cco;
	private String asunto;
	private String body;
	private String adjunto;
	
	public Email(){
		
	}

	/**
	 * @return the para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the cco
	 */
	public String getCco() {
		return cco;
	}

	/**
	 * @param cco the cco to set
	 */
	public void setCco(String cco) {
		this.cco = cco;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the adjunto
	 */
	public String getAdjunto() {
		return adjunto;
	}

	/**
	 * @param adjunto the adjunto to set
	 */
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	

}
