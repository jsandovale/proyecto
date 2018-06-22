import java.time.LocalDateTime;
import java.util.Random;

public class Llamada {
	public String numero;
	public String nombre;
	public LocalDateTime hora;
	
	
	public Llamada(int minutos) {
		Contacto aux = new Contacto();
		nombre = aux.nombre;
		numero = aux.numero;
		Random r = new Random();
		minutos = r.nextInt(minutos);
		hora = LocalDateTime.now().minusMinutes(minutos);
	}

	public String getNumero() {
		return numero;
	}
	
	public String getNombre() {
		return nombre;
	}
	public LocalDateTime getHora() {
		return hora;
	}
}
