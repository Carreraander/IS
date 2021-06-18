package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Login;
import domain.Question;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Piano", UtilDate.newDate(year,month-2,17), 0.00, "Abierto", "44444444J");
			Event ev2=new Event(2, "Guitarra", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017414J");
			Event ev3=new Event(3, "Bajo", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017145J");
			Event ev4=new Event(4, "Flauta", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017515J");
			Event ev5=new Event(5, "Xbox", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58047145J");
			Event ev6=new Event(6, "PS4", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58012145J");
			Event ev7=new Event(7, "PS5", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017145J");
			Event ev8=new Event(8, "Nintendo", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017112J");
			Event ev9=new Event(9, "Wii", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017345J");
			Event ev10=new Event(10, "Reloj", UtilDate.newDate(year,month,17), 0.00, "Abierto", "58017445J");

			Event ev11=new Event(11, "Diamante", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			Event ev12=new Event(12, "Television", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			Event ev13=new Event(13, "Mesa", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			Event ev14=new Event(14, "Cuchara", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			Event ev15=new Event(15, "Ordenador", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			Event ev16=new Event(16, "Portatil", UtilDate.newDate(year,month,1), 0.00, "Abierto", "58017145J");
			
			
			Question q1;
			Question q2;
	
			q1=ev1.addQuestion("22222222J",10.00);
			q2=ev1.addQuestion("22222222J",50.00);
	
			Login usuario = new Login("22222222J", "usuario@gmail.com","usuario", 999.00);
			Login admin = new Login("33333333J", "admin@admin.com","admin", 999.00);
			Login propietario = new Login("44444444J", "propietario@propietario.com","propietario", 999.00);
			
			db.persist(q1);
			db.persist(q2);

	
	        
			
			ev1.setMaxPujaDNI("22222222J");
			ev1.setMaxPuja(50);
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(usuario);
			db.persist(admin);
			db.persist(propietario);
			
			
			db.getTransaction().commit();
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
	public Question createQuestion(Event event, String question, float betMinimum, String DNI) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			TypedQuery<Login> query = db.createQuery("SELECT log FROM Login log WHERE log.dni=?1", Login.class);
			System.out.println("DNI Dado:" + DNI);
			query.setParameter(1, DNI);
			List<Login> login = query.getResultList();
			Login log = login.get(0);
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
//			db.getTransaction().begin();
//			db.remove(log);
//			db.getTransaction().commit();
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			ev.setMaxPujaDNI(DNI);
			ev.setMaxPuja(betMinimum);
			System.out.println("Bet:" + betMinimum);
			
			log.setSaldo(log.getSaldo() - betMinimum);
			System.out.println("DNI Obtenido en el query:" + log.getDni());
			System.out.println("DNI Obtenido en el query:" + log.getSaldo());
			
			
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			//db.persist(log);
			db.getTransaction().commit();
			return q;
		
	} 
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.estado='Abierto'",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	public Vector<Event> getEvents(String DNI) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.DNI=?1 AND ev.estado='Abierto'",Event.class);   
		query.setParameter(1, DNI);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	public Vector<Event> getPassedEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.estado='Abierto' AND ev.eventDate <= ?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	public Login login(String email, String contra) {
		//Basado en getEventsMonth (Cuidado con las comillas simples ')
		TypedQuery<Login> query = db.createQuery("SELECT login  FROM Login login WHERE login.email = '" + email + "' AND login.contra = '" + contra + "'",Login.class);   
		List<Login> l = query.getResultList();
		//Solo nos interesa el primero que aparezca
		return l.get(0);
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public void crearSubasta(String nombre, Date fecha, float pujaMin, String DNI) {
		/*
		 * Necesitamos:
		 * 	- Aumentar el numero de subastas (Query con las subastas de ese dia y al añadir la siguiente solamente es un mas uno)
		 *  - Crear el evento
		 *  - Persist
		 *  - Commit
		 */
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev ",Event.class);   
		List<Event> subastas = query.getResultList();
		db.getTransaction().begin();
		Event ev = new Event(subastas.size() + 1, nombre, fecha, pujaMin, "Abierto", DNI);
		ev.addQuestion(DNI, pujaMin);
		db.persist(ev);
		db.getTransaction().commit();
	}
	
	public void cerrarSubasta(int num) {
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber=?1", Event.class);
		query.setParameter(1, num);
		List<Event> subastas = query.getResultList();
		Event ev = subastas.get(0);
		TypedQuery<Login> query2 = db.createQuery("SELECT log FROM Login log WHERE log.dni=?2", Login.class);
		query2.setParameter(2, ev.getMaxPujaDNI());
		List<Login> login = query2.getResultList();
		Login log2 = login.get(0);
		TypedQuery<Login> query3 = db.createQuery("SELECT log FROM Login log WHERE log.dni=?3", Login.class);
		query3.setParameter(3, ev.getDNI());
		List<Login> login3 = query3.getResultList();
		Login log3 = login3.get(0);

		/*
		 * No vale con hacer un begin, remove, persist, commit hay que hacer lo siguiente:
		 */
		db.getTransaction().begin();
		db.remove(ev);
		db.remove(log2);
		db.remove(log3);
		db.getTransaction().commit();
		db.getTransaction().begin();
		ev.setEstado("Cerrado");
		log2.setSaldo(log2.getSaldo() - ev.getMaxPuja());
		log3.setSaldo(log3.getSaldo() + ev.getMaxPuja());
		System.out.println("usuario" + log2.getSaldo());
		System.out.println("Prop" + log3.getSaldo());
		

		db.persist(ev);
		db.persist(log2);
		db.persist(log3);
		db.getTransaction().commit();
		
	}
	
	public double obtenerSaldo(String DNI) {
		TypedQuery<Login> query = db.createQuery("SELECT log FROM Login log WHERE log.dni=?1", Login.class);
		System.out.println("DNI Dado:" + DNI);
		query.setParameter(1, DNI);
		List<Login> login = query.getResultList();
		Login log = login.get(0);
		return log.getSaldo();
	}

}