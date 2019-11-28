import java.net.*;
import java.util.Scanner;

public class Activitat1 {
	
	public static void main (String[] args) throws UnknownHostException {
		
		InetAddress dir = null;
		InetAddress dir2 = InetAddress.getLocalHost();
		
		try {

			// Cogemos lo que nos pasan por parametros
			dir = InetAddress.getByName(args[0]);
			
			// Cogemos los diferentes metodos que tiene la clase InetAddress
			// El metodo getByName devuelve el objecto InetAddress a partir del nombre
			System.out.println("\tMetode getByName() (devuelve el objecto InetAddress a partir del nombre): " + dir);
			// El metodo getHostName devuelve el nombre de host para el objeto
			System.out.println("\tMetode getHostName() (devuelve el nombre de host para el objeto): " + dir.getHostName());
			// El metodo getHostAddress devuelve la direccion IP del objeto
			System.out.println("\tMetode getHostAddress() (devuelve la direccion IP del objeto): " + dir.getHostAddress());
			// El metodo toString convierte la direccion IP en String
			System.out.println("\tMetode toString() (convierte la direccion IP en String): " + dir.toString());
			// El metodo getLoopbackAddress Devuelve la direccion de bucle invertido.
			System.out.println("\tMetode getLoopbackAddress() (devuelve la direccion de bucle invertido.): " + dir.getLoopbackAddress());
			// El metodo hashCode devuelve codigo hash para esta IP
			System.out.println("\tMetode hashCode() (devuelve codigo hash para esta IP): " + dir.hashCode());
			// El metodo isAnyLocalAddress rutina de utilidades para verificar si InetAddress esta en una direccion comodin
			System.out.println("\tMetode isAnyLocalAddress() (rutina de utilidades para verificar si InetAddress esta en una direccion comodin): " + dir.isAnyLocalAddress());
			// El metodo isLinkLocalAddress rutina de utilidades para verificar si InetAddress es una direccion local de enlace
			System.out.println("\tMetode isLinkLocalAddress() (rutina de utilidades para verificar si InetAddress es una direccion local de enlace): " + dir.isLinkLocalAddress());
			// El metodo isLoopbackAddress rutina de utilidad para verificar si InetAddress es una direccion de bucle invertido
			System.out.println("\tMetode isLoopbackAddress() (rutina de utilidad para verificar si InetAddress es una direccion de bucle invertido): " + dir.isLoopbackAddress());
			// El metodo isMCGlobal rutina de utilidad para verificar si la direccion de multidifusion tiene alcance global
			System.out.println("\tMetode isMCGlobal() (rutina de utilidad para verificar si la direccion de multidifusion tiene alcance global): " + dir.isMCGlobal());
			// El metodo isMCLinkLocal rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de enlace
			System.out.println("\tMetode isMCLinkLocal() (rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de enlace): " + dir.isMCLinkLocal());
			// El metodo isMCNodeLocal rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de nodo
			System.out.println("\tMetode isMCNodeLocal() (rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de nodo): " + dir.isMCLinkLocal());
			// El metodo isMCOrgLocal rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de organizacion
			System.out.println("\tMetode isMCOrgLocal() (rutina de utilidad para verificar si la direccion de multidifusion tiene alcance de organizacion): " + dir.isMCOrgLocal());
			// El metodo getLocalHost() obtiene informacion del equipo donde se ejecuta
			try {
				dir2 = InetAddress.getLocalHost();
				System.out.println("\tMetode getLocalHost() (obtiene informaciopn del equipo donde se ejecuta): " + dir2);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			// El metodo getLocalHost() obtiene el nombre de dominio completo para esta direccion IP
			System.out.println("\tMetode getCanonicalHostName() (obtiene el nombre de dominio completo para esta direccion IP): " + dir.getCanonicalHostName());
			// Array tipo InetAddress
			System.out.println("\tDireccion IP para: " + dir.getHostName());
			// El metodo getAllByName() dado el nombre de un host, devuelve una matriz de sus direcciones IP, en funcion del servicio de nombres configurado en el sistema.
			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
			for (int i = 0; i < adreces.length; i++)
				System.out.println("\t\t" + adreces[i].toString());

		} catch (UnknownHostException e1) {
			System.out.println("No se ha encontrado al IP o el nombre de la maquina.");
		}
	}
}
