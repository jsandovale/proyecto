import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
//import java.util.Calendar;
//import java.util.Date;



public class Actividad {
	public String nombre;
	public String descripcion;
	public LocalDate date;
	public LocalDateTime hora;
	
	
	public Actividad() {
		this.nombre = "";
		this.descripcion = "";
	}
	public Actividad(String nombre, String descripcion, int year, int month, int day, int hour, int minute, int second ) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.date =  LocalDate.of(year, month, day);
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setHora( LocalDate date, int hour, int minute ) {
		//this.date.atTime(hour, minute);;
	//	this.hora = LocalDateTime.of(year, month, day, hour, minute);
		LocalTime time = LocalTime.of(hour, minute);
		this.hora = LocalDateTime.of(date, time);
	}
	public LocalDateTime getHora() {
		return this.hora;
	}
	public void setDate(LocalDate date) {
		this.date = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		
	}
	public LocalDateTime getDate() {
		return this.hora;
	}
	/*public Calendar getDate() {
		return this.date;
	}
	public LocalDate getDate() {
		return this.date;
	}
*/
	
/*	public void setDate(Calendar date) {
		Calendar aux = new Calendar();
		this.date = date;
	}
	*/
	


}
