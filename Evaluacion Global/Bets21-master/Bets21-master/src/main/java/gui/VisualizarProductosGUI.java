package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domain.Event;
import domain.Login;
import businessLogic.BLFacade;
import configuration.UtilDate;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;


public class VisualizarProductosGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JTable tableEvents= new JTable();
	private DefaultTableModel tableModelEvents;

	private Login login;
	private JButton logout = new JButton(); 
	private JButton cerrarSubasta = new JButton(); 
	private int i;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"),
			//ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"),

	};

	/**
	 * This is the default constructor
	 */
	public VisualizarProductosGUI(Login login) {
		//super();

		this.login = login;
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				try {
//					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
//				}
//				System.exit(1);
//			}
//		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  

		this.getContentPane().setLayout(null);
			jLabelEvents.setBounds(19, 19, 259, 16);
			this.getContentPane().add(jLabelEvents);
			jLabelQueries.setBounds(138, 248, 406, 14);
			BLFacade facade=LoginGUI.getBusinessLogic();

			Vector<Event> events = facade.getEvents(login.getDni());
			
			//System.out.println(events);
			tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
				//tableModelEvents.setDataVector(null, columnNamesEvents);
			tableModelEvents.setColumnCount(3); // another column added to allocate ev objects
	
			
			//Vector<Event> events = facade.getPassedEvents();
			tableEvents.setModel(tableModelEvents);
			tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
			
			if (events.isEmpty()) {
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents"));
			
			}
			else {
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events"));
			}
				for (Event ev:events){
					Vector<Object> row = new Vector<Object>();
		
					System.out.println("Events " + ev + " PujaMax : " + ev.getMaxPuja());
		
					row.add(ev.getEventNumber());
					row.add(ev.getDescription());
		
					//row.add(ev.getMaxPuja());
					row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
					tableModelEvents.addRow(row);		
				}
				
			tableEvents.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					i = tableEvents.getSelectedRow();
				}
			});

			tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable

			scrollPaneEvents.setBounds(new Rectangle(19, 50, 346, 150));
			scrollPaneEvents.setViewportView(tableEvents);


			this.getContentPane().add(scrollPaneEvents, null);
	}






	
} // @jve:decl-index=0:visual-constraint="0,0"

