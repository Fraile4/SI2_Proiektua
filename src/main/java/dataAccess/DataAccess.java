package dataAccess;

import eredua.HibernateUtil;
import java.io.File;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import eredua.HibernateUtil;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess implements DataAccessInterface {
	//Session session = HibernateUtil.getSessionFactory().getCurrentSession();

     public DataAccess(boolean initializeMode)  {
		
		//System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open();
		
		/*if (initializeMode)
			initializeDB();
		*/
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		try {
			
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		   session.beginTransaction();
			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   /**
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
		    Event ev1=new Event("Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event("Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event("Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event("Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event("Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event("Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event("Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event("Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event("Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event("Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event("Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event("Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event("Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month,28));
		   
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			session.persist(q1);
			session.persist(q2);
			session.persist(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
	
	        
			session.persist(ev1);
			session.persist(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);
			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);			
			
			session.getTransaction().commit(); */
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		//System.out.println(session+" "+event);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//Event ev = (Event) session.get(Event.class, event.getEventNumber());
		Query query = session.createQuery("FROM Event WHERE eventNumber = :eventNumber");
		query.setParameter("eventNumber", event.getEventNumber());
		Event ev = (Event) query.uniqueResult();
			
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
		
		Question q = ev.addQuestion(question, betMinimum);
		q.setEvent(null);
		session.save(q);
		session.save(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		List<Event> res = new ArrayList<Event>();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate = :targetDate");
		query.setParameter("targetDate", date);
		List<Event> events = query.list();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	 session.getTransaction().commit();
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		session.beginTransaction();
		Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :startDate AND :endDate");   
		query.setParameter("eventDate", firstDayMonthDate);
		query.setParameter("startDate", lastDayMonthDate);
		List<Date> dates = query.list();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	session.getTransaction().commit();
	 	return res;
	}
	
	@Override
	public void open(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			/**
			System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
	
			String fileName=c.getDbFilename();
			
			
			if (c.isDatabaseLocal()) {
				  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
				  session = emf.createEntityManager();
			} else {
				Map<String, String> properties = new HashMap<String, String>();
				  properties.put("javax.persistence.jdbc.user", c.getUser());
				  properties.put("javax.persistence.jdbc.password", c.getPassword());
	
				  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
	
				  session = emf.createEntityManager();
	    	}
		*/
		session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("DB prestatzen.....");
	}

public boolean existQuestion(Event event, String question) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = (Event) session.get(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.close();
		System.out.println("DataBase closed");
	}

	@Override
	public void emptyDatabase() {
		// TODO Auto-generated method stub
		
	}

	
}

