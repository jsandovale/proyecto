import java.time.LocalDateTime;
import java.util.Random;

public class Mensaje {
	
	public static String[] Texto = { "hola" , "Chao", "Voy tarde" , "que haces en la noche" , "vienes en la noche?" , "Mañana hay tarea o no?", "Se llama o no se llama?"};
	
	public String nombre;
	public String numero;
	public String mensaje;
	public LocalDateTime hora;
	
	public Mensaje(int minutos) {
		Random r = new Random();
		Contacto aux = new Contacto();
		nombre = aux.nombre;
		numero = aux.numero;
		int i = r.nextInt(Texto.length);
		mensaje = Texto[i];
		minutos = r.nextInt(minutos);
		hora = LocalDateTime.now().minusMinutes(minutos);

	}

	public String getNombre() {
		return nombre;
	}
	public String getNumero() {
		return numero;
	}
	public String getMensaje() {
		return mensaje;
	}
	public LocalDateTime getHora() {
		return hora;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setNumero(String numero) {
		this.numero=numero;
	}
	public void setMensaje(String mensaje) {
		this.mensaje=mensaje;
	}
	










}
