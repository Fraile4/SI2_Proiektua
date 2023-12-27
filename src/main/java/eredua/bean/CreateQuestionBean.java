package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean{
	
	private BLFacade facade = FacadeBean.getBusinessLogic();
	//BLFacade facade = new BLFacadeImplementation (new DataAccess());
	private List<Event> events;
	private Event event;
	private Date data;
	private float minBet;
	private List<Question> questions;
	private List<Question> questions2;
	private String question;
	private Question galdera;
	
	public CreateQuestionBean() {
		
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
	public Float getMinBet() {
		return minBet;
	}
	public void setMinBet(Float minBet) {
		this.minBet = minBet;
	}
	public List<Question> getQuestions(){
		//this.questions = this.event.getQuestions();
		return this.questions;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Question getGaldera() {
		return this.galdera;
	}
	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}
	
	public void onDateSelect(SelectEvent event) {
		events = this.facade.getEvents((Date)event.getObject());
		this.event = null;
		this.question = null;
		this.questions2 = this.putQuestions();
	}
	public void onEventSelect(SelectEvent event) {
	    if (event != null && event.getObject() instanceof Event) {
	        this.event = (Event) event.getObject();
	        try {
	            //this.questions = this.facade.getQuestionsForEvent(this.event);
	        	this.questions = this.event.getQuestions();
	        } catch (Exception e) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error fetching questions for the selected event.", null));
	            e.printStackTrace(); // Log or handle the exception appropriately
	        }
	    } else {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid event selection.", null));
	    }
	}

	
	public void addQuestion() {

		if(event == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: gertaera bat aukeratu behar da"));
		}
		else if(this.question.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: deskribapen bat eman!"));	
		}
		else if(this.minBet <= 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Errorea: minimoak 0 baino handiagoa izan behar du"));
		}
		else {
			//BLFacade facadeBL = FacadeBean.getBusinessLogic();
			try {
				this.facade.createQuestion(this.event, this.question, this.minBet);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Galdera sortu da"));
				
				this.question = null;
	            this.minBet = 0.0f;
	            this.event = null;
	            
			} catch (EventFinished e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QuestionAlreadyExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//facadeBL = FacadeBean.getBusinessLogic();
			events = this.facade.getEvents(this.data);
		}
	}
	
	public List<Question> putQuestions() {
		events = this.facade.getEvents(data);
		List<Question> lista = new ArrayList<Question>();
		for (Event ev : events) {
			lista.addAll(ev.getQuestions());
		}
		return lista;
	}
	
}