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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	
	private JTextField tmail;
	private JPasswordField tcontra;
	private JLabel lmail;
	private JLabel lcontra;

	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * This is the default constructor
	 */
	public LoginGUI() {
		//super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

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
		/*
		 * Necesito un unico boton abajo y dos campos de texto superiores
		 */
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(7, 3, 0, 0));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getLmail());
			jContentPane.add(getTmail());
			jContentPane.add(getLcontra());
			jContentPane.add(getTcontra());
			jContentPane.add(getLogin());
			jContentPane.add(getPanel());
		}
		return jContentPane;
	}

	/*
	 * Crea el texto Mail
	 */
	private JLabel getLmail() {
		if (lmail == null) {
			lmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
			lmail.setFont(new Font("Tahoma", Font.BOLD, 13));
			lmail.setForeground(Color.BLACK);
			lmail.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lmail;
	}
	
	private JTextField getTmail() {
		if (tmail == null) {
			tmail = new JTextField();
		}
		return tmail;
	}
	private JLabel getLcontra() {
		if (lcontra == null) {
			lcontra = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contra"));
			lcontra.setFont(new Font("Tahoma", Font.BOLD, 13));
			lcontra.setForeground(Color.BLACK);
			lcontra.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lcontra;
	}
	
	private JTextField getTcontra() {
		if (tcontra == null) {
			tcontra = new JPasswordField();
		}
		return tcontra;
	}

	/**
	 * Genera el boton de "Login"
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getLogin() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				/*
				 * Si el boton es pulsado: Muestra el GUI de Crear Pujas
				 * FIX: Facade is null
				 */
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade = LoginGUI.getBusinessLogic();
					//Hay que convertir la contraseña a string ya que el metodo getPassword devuelve un array de char
					Login login = facade.login(tmail.getText(),new String(tcontra.getPassword()));
					hacerLogin(login);
				}
			});
		}
		return jButtonCreateQuery;
	}

/*
 * Crea el texto "Seleccionar Opcion" 
 */
	
	private void hacerLogin(Login login) {
		
		//BLFacade facade = LoginGUI.getBusinessLogic();
		if (login.isPropietario()) {
			PropietarioGUI prop = new PropietarioGUI(this, login);
			prop.setVisible(true);
			
		}
		else if (login.isAdmin()) {
			AdminGUI admin = new AdminGUI(this,login);
			admin.setVisible(true);
		}
		else {
			UsuarioGUI user = new UsuarioGUI(this, login);
			user.setVisible(true);
		}
		this.setVisible(false);
	}
	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
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
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lmail.setText(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lcontra.setText(ResourceBundle.getBundle("Etiquetas").getString("Contra"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

