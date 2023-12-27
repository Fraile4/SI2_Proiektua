package eredua.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.primefaces.event.SelectEvent;

import businessLogic.*;
import dataAccess.DataAccess;
import domain.Event;
import eredua.HibernateUtil;

public class Bean {
	
	//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	BLFacade facadeBL = FacadeBean.getBusinessLogic();
	//BLFacade facadeBL = new BLFacadeImplementation (new DataAccess()); // Negozioaren logika sortu
	List<Event> etxeak=facadeBL.getEvents(null); // Neg. logikara deitu

	private String izena;
	private String pasahitza;
	private int id = 5;
	private Event event;
	private static List<Event> events = new ArrayList<Event>();
	
	private Date data;

	public Bean(){ }

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}
	
	public int getId() {
		return this.id;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this. pasahitza = pasahitza;
	}

	public String egiaztatu() {
		if (izena.equals("pirata")) return "error";
		else return "ok";
	}
	
	public String createQuery() {
		return "create";
	}
	
	public String queryQueries() {
		return "find";
	}
	
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public static List<Event> getEvents() {
		return events;
	}

	public static void setEvents(List<Event> events) {
		//FacadeBean.events = events;
	}
	
	public Date getData() {
		return this.data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
		 new FacesMessage("Data aukeratua: "+event.getObject()));
		//events = BLFacade.getEvents(data);
	}

}