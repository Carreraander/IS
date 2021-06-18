package gui;

import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Login;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;

public class CreateSubastaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private Login login;
	private JCalendar jCalendar = new JCalendar();

	private JScrollPane scrollPaneEvents = new JScrollPane();
	
	private final JLabel jNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jNombre")); 
	private final JTextField tNombre = new JTextField(); 
	private final JLabel jFecha = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jFecha")); 
	private final JLabel jPujaMin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("jPujaMin")); 
	private final JTextField tPujaMin = new JTextField(); 
	private final JButton crearSubasta = new JButton(); 
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
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
		jLabelMsg.setBounds(new Rectangle(117, 242, 305, 20));
		jLabelMsg.setForeground(Color.red);
		jLabelError.setBounds(new Rectangle(125, 242, 305, 20));
		jLabelError.setForeground(Color.red);
		crearSubasta.setBounds(new Rectangle(97, 263, 333, 59));
		crearSubasta.setText(ResourceBundle.getBundle("Etiquetas").getString("jCrearSubasta"));
		crearSubasta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tNombre != null) {
						if (tPujaMin != null) {
							//Todo esta rellenado correctamente
							
							BLFacade facade = LoginGUI.getBusinessLogic();
							Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
							facade.crearSubasta(tNombre.getText(),firstDay,Float.parseFloat(tPujaMin.getText()), login.getDni());
							jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("SubastaCreada"));
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
				} catch (EventFinished e1) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				} catch (EventAlreadyExists e1) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
				} catch (java.lang.NumberFormatException e1) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				} catch (Exception e1) {
					e1.printStackTrace();
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
		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		
	}
}
	