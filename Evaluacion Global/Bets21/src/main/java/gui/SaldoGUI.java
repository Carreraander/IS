package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Login;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SaldoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;


    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private JLabel jSaldo;
	private JLabel tSaldo;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;

	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private Login login;
	
	/**
	 * This is the default constructor
	 */
	public SaldoGUI(Login login) {

		this.login = login;
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
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * Genera el panel inicial
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(3, 3, 0, 0));

			jContentPane.add(jsaldo());
			jContentPane.add(tsaldo());
			jContentPane.add(getPanel());	
		}
		return jContentPane;
	}

	private JLabel jsaldo() {
		jSaldo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Saldo"));
		jSaldo.setFont(new Font("Tahoma", Font.BOLD, 13));
		jSaldo.setForeground(Color.BLACK);
		jSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		return jSaldo;
	}
	private JLabel tsaldo() {
		BLFacade facade=LoginGUI.getBusinessLogic();
		System.out.println("DNI 3:" + login.getDni());
		System.out.println("DNI 3:" + login.getSaldo());
		tSaldo = new JLabel();
		tSaldo.setText(String.valueOf(facade.obtenerSaldo(login.getDni())));
		tSaldo.setFont(new Font("Tahoma", Font.BOLD, 13));
		tSaldo.setForeground(Color.BLACK);
		tSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		return tSaldo;
	}
	
/*
 * Botones en diferentes idiomas
 * En caso de tocar uno de los botones de idioma cambia el Locale a el deseado y redibuja 
 */
	
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Saldo"));

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	
} // @jve:decl-index=0:visual-constraint="0,0"

