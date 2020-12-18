package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurReservation implements Runnable{

	private ServerSocket listen_socket;
	
	public ServeurReservation(int portreservation) throws IOException {
		listen_socket = new ServerSocket(portreservation);
	}
	

	@Override
	public void run() {
		try {
			System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
			while(true)
				new Thread(new ServiceReservation(listen_socket.accept())).start();
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Arr�t du serveur au port "+this.listen_socket.getLocalPort());
		}
	}
	
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
	
}
