import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

	private clientController cont;
	private String ip;
	public Data message;
	private Client c;
    private Socket clientSocket;

	
	public ClientThread(Socket socket, clientController cont, String ip, Client c, Data message1) {
        this.clientSocket = socket;
		this.cont = cont;
		this.setIp(ip);
		this.message = message1;
		this.c = c;
	}
	
	public void run() {
		super.run();
		try{
			handleReadAndWrite(clientSocket);
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}  

	public void handleReadAndWrite(Socket s) throws Exception {
		OutputStream outputStream = s.getOutputStream();
		ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
					
		InputStream inputStream = s.getInputStream();
		ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		
		//send data to server
		message.setMessage1("connected! now you can talk!!");
		objOutputStream.writeObject(message);
			
		// get updated Data from server
		message = (Data)objInputStream.readObject();
		this.cont.getTextArea().appendText(message.getMessage1());
		
		sleep(3000);
		cont.getTextArea().clear();
		String mess;
		
		while(!cont.getTextField().isDisabled()) {
			
			//calling readfromuser method to get user message
			mess = this.readFromUser();
			message.setMessage1(mess);
			//send data to the server
			objOutputStream.writeObject(message);
				
			// get updated Data from server
			message = (Data)objInputStream.readObject();
			
			if(!message.getMessage1().equals("")) {
				this.cont.getTextArea().appendText(message.getMessage1());
			}
		}
		//when disconnect pressed the text field disabled then the while loop ends and sending "disconnect" to the server
		//to close the server.
		message.setMessage1("disconnected");
		objOutputStream.writeObject(message);
			
		// get updated Data from server
		message = (Data)objInputStream.readObject();
		this.cont.getTextArea().appendText(message.getMessage1());
		
		outputStream.close();
		objOutputStream.close();
		inputStream.close();
		objInputStream.close();
		s.close();
	}
	
	private synchronized String readFromUser() {
		String input = null;
		try {
			sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//getting the message from user.
		input = cont.getTextField().getText() +"\n";
		cont.getTextField().clear();
		return input;
	}

	public Client getC() {
		return c;
	}
	
	public void setC(Client c) {
		this.c = c;
	}

	public Data getMessage() {
		return message;
	}

	public void setMessage(Data message) {
		this.message = message;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
