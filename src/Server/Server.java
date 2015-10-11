package Server;

import java.io.*;
import java.net.*;

public class Server {
	private int port;
	private ServerSocket server;
	private int count=0;
	
	public Server(int porta) {
		port=porta;
	}
	
	public void apriconn(){
		Socket client;
		String command;
		DataInputStream in;
		DataOutputStream out;
		
		try {
				
				System.out.println("In attesa di connessione sul porto "+port);
				server= new ServerSocket(port);
				client=server.accept();
				System.out.println("Connessione stabilita..");
				
				in=new DataInputStream(client.getInputStream());
				out=new DataOutputStream(client.getOutputStream());
				
				do{
					System.out.println("Server in attesa di comandi..");
					command= in.readUTF();
					System.out.println("Comando: "+command);
					
					if(command.compareTo("Incrementa")==0){
						count=count+in.readInt();
					}
					else if(command.compareTo("Leggi")==0){
						out.writeInt(count);
					}
					else if(command.compareTo("Bye")==0){
						System.out.println("Ciao!");
					}
					else
						System.out.println("Comando sconosciuto!");
					
				}while(command.compareTo("Bye")!=0);
				
				in.close();
				out.close();
				server.close();
				
				System.out.println("Chiusura Server.."); 
				
		} catch (IOException e) {
				System.err.println("Tentativo di stabilire una connessione fallito..");
				e.printStackTrace();
			}		
		}
}
