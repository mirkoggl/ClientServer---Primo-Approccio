package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Client {
	private String indirizzo;
	private int port;
	private Socket c;
	DataInputStream in;
	DataOutputStream out;
	
	public Client (String s,int x){
		indirizzo=s;
		port=x;
	}
	
	public void connetti(){
		System.out.println("Tentativo di connessione a "+indirizzo+":"+port);
		try {
			c=new Socket(indirizzo,port);
			System.out.println("Connessione stabilita..");			
		}
		catch (IOException e) {
			System.err.println("Tentativo di stabilire una connessione fallito..");
			e.printStackTrace();
		}
	}
	
	public void todo(){
		
		try{
		
			in=new DataInputStream(c.getInputStream());
			out=new DataOutputStream(c.getOutputStream());
			
			
			out.writeUTF("Incrementa");
			out.writeInt(40);
			
			out.writeUTF("Leggi");
			System.out.println("Valore Letto da Server: "+in.readInt());
			
			out.writeUTF("Incrementa");
			out.writeInt(60);
			
			out.writeUTF("Leggi");
			System.out.println("Valore Letto da Server: "+in.readInt());
			
			out.writeUTF("FaQuelloCheVuoi");
			
			out.writeUTF("Bye");
						
			in.close();
			out.close();
			c.close();
			System.out.println("Connessione con "+indirizzo+":"+port+" chiusa..");
		}
		catch(IOException e){
			System.err.println("Errore Client..");
			e.printStackTrace();
		}
		
	}
}
