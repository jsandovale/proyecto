import java.util.Random;

public class Contacto {
	
	
	public static String[][] contactos = { { "Desconocido" , "0" }, {"Mama" , "9837463"}, {"Papa", "8266352"} ,{"Jesus", "3238746"} , {"Maria"," 2004873" } ,
			{"Jose", "8963542"} , {"Judas", "7645364" }, { "Pablo", "8842235" } , { "Lucas"," 8745673" }, { "Profe", "7645384 "}};

	public String nombre;
	public String numero;
	
	public Contacto(){
		Random r = new Random();
		int i = r.nextInt(contactos.length);
		nombre = contactos[i][0];
		if(i==0) // desconocido
			numero = String.valueOf(r.nextInt(8999999) + 10000000);
		else
			numero = contactos[i][1];
		}
}
