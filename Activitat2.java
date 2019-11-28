import java.net.*;
import java.io.*;

public class Activitat2 {
	
	public static void main (String[] args) {
		
		// Variables que usaremos
		URL url = null;
		
		try {
			
			// Cogemos el puerto que nos pasan por parámetros y lo pasamos a int
			int puerto = Integer.parseInt(args[1]);	
			
			// Cogemos las URLs y se las pasamos por parámetros a la clase URL
			url = new URL("http", args[0], puerto, "/");
			
		} catch (MalformedURLException e) {
			e.printStackTrace(); 
		}
		
		// Llamamos al método que leerá el código de ambas URLs
		recuperaCodigo(url);
	}
	
	// Clase que utilizaré para recuperar el código de la web
	public static void recuperaCodigo(URL url) {

		// Utilizamos la siguiente clase que nos permite leer el texto de la web
		BufferedReader in;

		try {

			// Leemos la URL
			InputStream inputStream1 = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream1));

			// String para guardar los datos
			String inputLine;

			// Guardamos los datos hasta que no devuelva null
			while ((inputLine = in.readLine()) != null)
				// Mostramos esos datos
				System.out.println(inputLine);
			// Cerramos el archivo
			in.close();

		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
}

