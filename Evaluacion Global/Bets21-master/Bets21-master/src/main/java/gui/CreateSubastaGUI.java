package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Login;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateSubastaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private Login login;
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private JCalendar jCalendar = new JCalendar();

	private JScrollPane scrollPaneEvents = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JLabel jNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jNombre")); 
	private final JTextField tNombre = new JTextField(); 
	private final JLabel jFecha = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jFecha")); 
	private final JLabel jPujaMin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jPujaMin")); 
	private final JTextField tPujaMin = new JTextField(); 
	private final JButton crearSubasta = new JButton(); 
	public CreateSubastaGUI(Vector<domain.Event> v, Login login) {
		this.login = login;
		
		

		
	
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateSubasta"));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		
		jCalendar.setBounds(new Rectangle(205, 50, 225, 150));
		jNombre.setBounds(new Rectangle(97, 11, 100, 30));
		tNombre.setBounds(new Rectangle(207, 11, 223, 30));
		jFecha.setBounds(new Rectangle(97, 52, 100, 30));
		jPujaMin.setBounds(new Rectangle(97, 208, 100, 30));
		tPujaMin.setBounds(new Rectangle(207, 208, 223, 30));
		crearSubasta.setBounds(new Rectangle(97, 263, 333, 59));
		crearSubasta.setText(ResourceBundle.getBundle("Etiquetas").getString("jCrearSubasta"));
		crearSubasta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tNombre != null) {
					if (tPujaMin != null) {
						//Todo esta rellenado correctamente
						
						BLFacade facade = LoginGUI.getBusinessLogic();
						Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
						facade.crearSubasta(tNombre.getText(),firstDay,Float.parseFloat(tPujaMin.getText()), login.getDni());
					}
					else {
						/*
						 * No contiene una puja minima
						 */
					}
				}
				else {
					/*
					 * No contiene nombre de producto
					 */
				}
			}
		});
		
		this.getContentPane().add(jNombre, null);
		this.getContentPane().add(tNombre, null);
		this.getContentPane().add(jFecha, null);
		this.getContentPane().add(jPujaMin, null);
		this.getContentPane().add(tPujaMin, null);
		this.getContentPane().add(crearSubasta, null);
		this.getContentPane().add(jCalendar, null);
		

		
		
		BLFacade facade = LoginGUI.getBusinessLogic();
	}

	
//public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
//		
//		int month = calendar.get(Calendar.MONTH);
//		int today=calendar.get(Calendar.DAY_OF_MONTH);
//		int year=calendar.get(Calendar.YEAR);
//		
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		int offset = calendar.get(Calendar.DAY_OF_WEEK);
//
//		if (Locale.getDefault().equals(new Locale("es")))
//			offset += 4;
//		else
//			offset += 5;
//		
//		
//	 	for (Date d:datesWithEventsCurrentMonth){
//
//	 		calendar.setTime(d);
//	 		System.out.println(d);
//	 		
//
//			
//			// Obtain the component of the day in the panel of the DayChooser of the
//			// JCalendar.
//			// The component is located after the decorator buttons of "Sun", "Mon",... or
//			// "Lun", "Mar"...,
//			// the empty days before day 1 of month, and all the days previous to each day.
//			// That number of components is calculated with "offset" and is different in
//			// English and Spanish
////			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
//			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
//					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
//			o.setBackground(Color.CYAN);
//	 	}
//	 	
// 			calendar.set(Calendar.DAY_OF_MONTH, today);
//	 		calendar.set(Calendar.MONTH, month);
//	 		calendar.set(Calendar.YEAR, year);
//
//	 	
//	}

}