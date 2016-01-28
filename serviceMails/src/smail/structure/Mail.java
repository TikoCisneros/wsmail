package smail.structure;


import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import smail.entities.Email;
import smail.entities.WsMailYachay;


public class Mail {
	
	/**
	 * Envía Correos a destinatario y copias sin adjunto
	 * @param estructura
	 * @param server
	 * @throws Exception
	 */
	public static void generateAndSendEmail(Email estructura, WsMailYachay server) throws Exception
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", server.getMaiServer());
        props.put("mail.from", server.getMaiUsuario());
        props.put("mail.smtp.starttls.enable", "false"); 
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Authenticator authenticator = new Authenticator(server.getMaiUsuario().split("@")[0].toString(),server.getMaiPwd());
        props.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
        BodyPart texto = new MimeBodyPart();
        texto.setContent(estructura.getBody(), "text/html; charset=utf-8");
        
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
               
        Session session = Session.getInstance(props, authenticator);
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(server.getMaiUsuario()));
        msg.addRecipients(Message.RecipientType.TO,InternetAddress.parse(estructura.getPara()));
        if(estructura.getCc()!=null)
        	msg.setRecipients(Message.RecipientType.CC, estructura.getCc());
        if(estructura.getCco()!=null)
        	msg.setRecipients(Message.RecipientType.BCC, estructura.getCco());
        msg.setSubject(estructura.getAsunto());
        msg.setContent(multiParte);
        msg.setFrom();
     
        Transport transport = session.getTransport("smtp");
        transport.connect();
        msg.saveChanges(); 
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("ENVIO DE MENSAJE CORRECTO PARA "+estructura.getPara());
    }
	
	
}