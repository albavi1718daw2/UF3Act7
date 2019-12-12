import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class ClientSMTP2 {

	public static void main (String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {

		Scanner teclado = new Scanner(System.in);
		
		// Creamos el cliente SMTP seguro
		AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

		// Datos del usuario y el servidor que recibimos por parámetros
		String server = "smtp.gmail.com";
		System.out.println("Introduce tu usuario: ");
		String username = teclado.nextLine();
		System.out.println("Introduce tu contraseña: ");
		String contrasenya = teclado.nextLine();
		int port = 587;

		try {
			
			int resposta;
			
			// Creamos la clave para establecer el canal seguro
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(null, null);
			KeyManager km = kmf.getKeyManagers() [0];

			// Nos conectamos al servidor SMTP
			client.connect(server, port);
			System.out.println("1 - "+client.getReplyString());

			// Se establece la clave para una conexión segura
			client.setKeyManager(km);

			resposta = client.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(resposta)) {

				client.disconnect();
				System.err.println("NO ES POSIBLE LA CONEXIÓN.");
				System.exit(1);
			}

			// Se envía la orden EHLO
			client.ehlo(server); // Tenemos que hacerlo
			System.out.println("2 - "+client.getReplyString());

			// Se ejecuta la orden STARTTLS y se comprueba si es true
			if (client.execTLS()) {

				System.out.println("3 -"+client.getReplyString());

				// Se hace la autenticación con el servidor
				if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, contrasenya)) {

					System.out.println("4 -"+client.getReplyString());
					
					System.out.println("Introduce el destinatario: ");
					String desti1 = teclado.nextLine();
					System.out.println("Introduce el asunto: ");
					String asumpte = teclado.nextLine();
					System.out.println("Introduce el mensaje: ");
					String missatge = teclado.nextLine();

					// Se crea la cabecera
					SimpleSMTPHeader capcalera = new SimpleSMTPHeader(username, desti1, asumpte);

					// El nombre de usuario y el email coinciden
					client.setSender(username);
					client.addRecipient(desti1);
					System.out.println("5 -"+client.getReplyString());

					// Se envía DATA
					Writer writer = client.sendMessageData();

					if (writer == null) {
						System.out.println("No se puede enviar la DATA");
						System.exit(1);
					}

					writer.write(capcalera.toString()); // Cabecera
					writer.write(missatge); // El mensaje
					writer.close();
					System.out.println("6 -"+client.getReplyString());

					boolean exit = client.completePendingCommand();
					System.out.println("7 -"+client.getReplyString());

					if (!exit) {
						System.out.println("Error al finalizar la transacción");
						System.exit(1);
					}

				} else {

					System.out.println("Usuario no autenticado");
				}

			} else {

				System.out.println("Error al ejecutar STRATTLS");
			}
		} catch (IOException e) {

			System.out.println("No se puede conectar con el servidor");
			e.printStackTrace();
			System.exit(1);

		}

		try {
			client.disconnect();
		} catch (IOException f) {f.printStackTrace(); }

		System.out.println("Final del envío");
		System.exit(0);
	}
}