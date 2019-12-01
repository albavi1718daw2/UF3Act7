import java.io.*;
import java.net.*;

public class ClientUDP2 {

	public static void main (String[] args) throws Exception {
		
		// Variables
		boolean respuesta = true;

		// Flujo para entrada estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Este es el socket del cliente
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviats = new byte[1024];
		byte[] rebuts = new byte[1024];
		
		// Servidor al que enviaremos el mensaje
		InetAddress IPServidor = InetAddress.getLocalHost();
		int port = 9800;
		
		// Bucle para preguntar cuál es el mensaje, mientras tenga respuesta (respuesta = true)
		// seguirá ejecutándose
		while (respuesta) {
			
		// Introducimos los datos por el teclado
		System.out.print("Introdueix missatge: ");
		String cadena = in.readLine();
		enviats = cadena.getBytes();

		// Enviamos datagrama al servidor
		System.out.println("Enviant "+enviats.length+"bytes al servidor.");
		DatagramPacket enviament = new DatagramPacket(enviats, enviats.length, IPServidor, port);
		clientSocket.send(enviament);

		// Recibiendo datagrama del servidor
		DatagramPacket rebut = new DatagramPacket(rebuts, rebuts.length);
		System.out.println("Esperant datagrama...");
		
		// Aquí es donde ponemos que el tiempo de espera serán 5 segundos
		clientSocket.setSoTimeout(5000);
		
		try {
			clientSocket.receive(rebut);
			String majuscula = new String(rebut.getData());
	
			// Conseguimos información del datagrama
			InetAddress IPOrigen = rebut.getAddress();
			int portOrigen = rebut.getPort();
			System.out.println("\tProcedent de: "+IPOrigen+":"+portOrigen);
			System.out.println("\tDades: "+majuscula.trim());
			
		// En caso de que supere el tiempo de 5 segundos, lanzará la
		// siguiente excepción y saldremos del bucle
		} catch(SocketTimeoutException e) {
			  	System.out.println("\nTiempo excedido");
			  	respuesta = false;
			 }
		}

		// Cerramos el socket del cliente
		clientSocket.close();
	}
}