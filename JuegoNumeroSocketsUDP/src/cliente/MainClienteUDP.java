package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MainClienteUDP {
	
    public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		int numeroCliente;
		int puerto_Servidor = 6000;

		System.out.println("Cliente conectado...");

		InetAddress direcion_servidor = InetAddress.getByName("localhost");

		
			// inicializamos cliente
			DatagramSocket socketCliente = new DatagramSocket();

			while (true) {

				System.out.println("Introduce un numero: ");
				numeroCliente = sc.nextInt();

				// enviamos numero al servidor
				InetAddress direcion = InetAddress.getByName("localhost");
				byte[] enviar = String.valueOf(numeroCliente).getBytes();
				DatagramPacket paqueteEnviar = new DatagramPacket(enviar, enviar.length, direcion, puerto_Servidor);
				socketCliente.send(paqueteEnviar);

				// recibir respuesta servidor
				byte[] recibido = new byte[1024];
				DatagramPacket paqueteRecibido = new DatagramPacket(recibido, recibido.length);
				socketCliente.receive(paqueteRecibido);

				String respuesta = new String(paqueteRecibido.getData()).trim();

			
				//System.out.println(respuesta);
				// si el servidor me notifica acertado, finalizo el programa
				if (respuesta.equals("Acertado")) {
					System.out.println("Has acertado el numero!!");
					socketCliente.close();
					System.out.println("Fin del programa");
					break;
				} else {
					System.out.println("El numero es " + respuesta);
				}
			}

		
		
		
	}
}
