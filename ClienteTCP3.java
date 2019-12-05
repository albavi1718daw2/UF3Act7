import java.net.*;
import java.io.*;

public class ClienteTCP3 {

	public static void main (String[] args) throws Exception {

		String host = "localhost";
		int port = 9000; // Puerto remoto
		Socket client = new Socket(host, port);
		
		// Variables utilizadas para enviar y recibir mensajes
		String cadena, msj = "";

		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);

		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));

		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Recibe mensaje del servidor al conectarse
		msj = fentrada.readLine();
		System.out.println(msj);
		
		// Variables utilizadas para enviar un mensaje
		System.out.println("Introduce el mensaje: ");
		//Lectura teclat
		cadena = in.readLine();

		while (cadena != null) {

			//Enviament cadena al servidor
			fsortida.println(cadena);
			// Recibe mensaje del servidor
			msj = fentrada.readLine();
			System.out.println( "=> ECO: " + msj);
			//Lectura del teclat
			cadena = in.readLine();	
		}

		fsortida.close();
		fentrada.close();
		System.out.println("Finaliza el envío...");
		in.close();
		client.close();	
	}	
}
