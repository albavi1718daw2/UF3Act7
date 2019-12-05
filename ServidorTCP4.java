import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP4 {

	public static void main (String[] args) throws Exception {

		// Puerto por el cual nos conectaremos
		int numPort = 9000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";

		// Variables que usaremos
		int clientes = 0;
		PrintWriter fsortida;
		BufferedReader fentrada;

		// Bucle para ir contand los clientes, mientras sea menor a 3, sigue
		while (clientes < Integer.parseInt(args[0])) {
			
			// Conectamos al cliente
			System.out.println("Esperando conexión...");
			Socket clienteConectado = servidor.accept();
			
			// Indicamos qué cliente se acaba de conectar
			PrintWriter fcliente = new PrintWriter(clienteConectado.getOutputStream(), true);
			fcliente.println("Cliente " + (clientes + 1) + " conectado.");
			
			// Muestra en el servidor el cliente que está conectado
			System.out.println("Cliente " + (clientes + 1) + " conectado.");

			// Flujo de salida al cliente
			fsortida = new PrintWriter(clienteConectado.getOutputStream(), true);
			// Flujo de entrada del cliente
			fentrada = new BufferedReader(new InputStreamReader(clienteConectado.getInputStream()));

			// Si el cliente escribe mensajes, los mostramos
			while ((cadena = fentrada.readLine()) != null) {

				fsortida.println(cadena);
				System.out.println("Recibiendo: " + cadena);
				// Si el usuario escribe *, lo pasamos
				if (cadena.equals("exit")) {
					break;
				}
			}

			// Cerramos la entrada y la salida
			fentrada.close();
			fsortida.close();

			clientes++;
		}

		// El servidor deja de escuchar paquetes
		servidor.close();
	}
}

