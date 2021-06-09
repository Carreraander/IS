package domain;

public class Login {
	private String email;
	private String contra;
	private String dni;
	private boolean admin = false;
	private boolean propietario = false;
	//Suponemos que puede tener saldo con decimales
	private double saldo;
	
	public Login(String email, String contra) {
		this.email = email;
		this.contra = contra;
		if (email.contains("@admin.com")) {
			this.admin = true;
		}
		else if (email.contains("@propietario.com")) {
			this.propietario = true;
		}
	}
	
	public Login(String dni,String email, String contra) {       
    	this.dni = dni;
		this.email = email;                           
		this.contra = contra;                         
		if (email.contains("@admin.com")) {           
			this.admin = true;                        
    	}                                             
		else if (email.contains("@propietario.com")) {
			this.propietario = true;                  
		}                                             
    }                                                 
	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isPropietario() {
		return propietario;
	}

	public void setPropietario(boolean propietario) {
		this.propietario = propietario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
