package com.otmanel.struts2_spring_jpaFirst.actions;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.dialect.MySQL5InnoDBDialect;

import com.opensymphony.xwork2.ActionSupport;
import com.otmanel.struts2_spring_jpaFirst.metier.Message;

public class IndexAction extends ActionSupport {
	
	private static Logger log = LogManager.getLogger(ActionSupport.class); 
	private static final long serialVersionUID = 1L;

	private Message message;

	public Message getMessage() {return message;}
	public void setMessage(Message message) {this.message = message;}

	public String index() {
		//MySQL5InnoDBDialect
		log.info("appel de index!");
		return SUCCESS;
	}

}
