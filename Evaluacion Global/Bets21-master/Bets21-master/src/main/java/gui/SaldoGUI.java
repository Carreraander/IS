package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;


import domain.Login;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;


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
	
} // @jve:decl-index=0:visual-constraint="0,0"

