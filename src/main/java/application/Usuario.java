package application;


public class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private String correo;
	private String contra;
	
	public Usuario() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getContra() {
		return contra;
	}
	
	public void setContra(String contra) {
		this.contra = contra;
	}
	
}
