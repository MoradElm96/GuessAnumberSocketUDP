package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainServerUDP {

	public static void main(String[] args) throws IOException {

		
		try {
			
		int puertoSocket = 6000;
		// generamos un numero aleatorio entre 1 y 100
		int numeroAdivinar = (int) (Math.random() * 100 + 1);
		System.out.println("///Respuesta para hacer trampa y acabar antes: "+ numeroAdivinar+ " ///");
		
		   //iniciamos el servidor 
			DatagramSocket socketServidor = new DatagramSocket(puertoSocket);
			byte[] recibido = new byte[1024];
			
			
			while (true) {
				
				System.out.println("Servidor escuchando... esperando al cliente ");
				DatagramPacket paqueteRecibido = new DatagramPacket(recibido, recibido.length);         	
				//recibimos el paquete del cliente
				socketServidor.receive(paqueteRecibido);
				
				
				//obtenemos informacion del cliente
				InetAddress direccion = paqueteRecibido.getAddress();
				int puerto = paqueteRecibido.getPort();
				
				String numeroRecibido = new String(paqueteRecibido.getData()).trim();
				int numeroClienteInt = Integer.valueOf(numeroRecibido.trim());
			    System.out.println("cliente dice " + numeroClienteInt);
			 
			    
				// comprobamos 
			    String resultado="";
			    if(numeroClienteInt < numeroAdivinar) {
			    	resultado="Tiene que ser Mayor";
			    	//enviamos respuesta al cliente
					byte [] enviar = resultado.getBytes();
					DatagramPacket respuesta = new DatagramPacket(enviar,  enviar.length, direccion, puerto);
				    socketServidor.send(respuesta);
			    	
			    }else if(numeroClienteInt>numeroAdivinar) {
			    	resultado ="Tiene que ser Menor";
			    	//enviamos respuesta al cliente
					byte [] enviar = resultado.getBytes();
					DatagramPacket respuesta = new DatagramPacket(enviar,  enviar.length, direccion, puerto);
				    socketServidor.send(respuesta);
			    	
			    }else {
			    	resultado ="Acertado";
			    	//enviamos respuesta al cliente
					byte [] enviar = resultado.getBytes();
					DatagramPacket respuesta = new DatagramPacket(enviar,  enviar.length, direccion, puerto);
				    socketServidor.send(respuesta);
				    socketServidor.close();
			    	break;
			    }
			    
			    

			}

		

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}