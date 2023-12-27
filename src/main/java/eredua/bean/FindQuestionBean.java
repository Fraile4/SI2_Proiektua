package eredua.bean;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;

public class FindQuestionBean {
	
	private BLFacade facade = FacadeBean.getBusinessLogic();
	private List<Event> events;
	private Event event;
	private Date data;
	private String question;
	
	public FindQuestionBean() {
		
	}
	
	public BLFacade getFacade() {
		return facade;
	}
	public void setFacade(BLFacade facade) {
		this.facade = facade;
	}
	public List<Event> getEvents() {
		this.events = this.facade.getEvents(data);
		return events;
	}
	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void onDateSelect(SelectEvent event) {
		events = this.facade.getEvents((Date)event.getObject());
		this.event = null;
	}
	
	public void onEventSelect(SelectEvent event) {
		//this.event = (Event) event.getObject();
		//System.out.println(this.event);
		//BLFacade facadeBL=FacadeBean.getBusinessLogic();
		//eventList=facadeBL.getEvents(this.eguna);
		//System.out.println(event.toString());
	}
	
}
