import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private boolean connect = true;
	private Socket s = null;
	
	InputStream inputStream;
	OutputStream outputStream;
	ObjectOutputStream objOutputStream = null;
	ObjectInputStream objInputStream = null;
	Data d ;
	
	public ServerThread(Socket socket) throws Exception {
		this.s = socket;
		outputStream = s.getOutputStream();
		inputStream = s.getInputStream();
		
		start();
	}
	
	@Override
	public void run() {
		super.run();
		try {
			objOutputStream = new ObjectOutputStream(outputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			objInputStream = new ObjectInputStream(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			//while no use have been disconnect conitnue to run.
			while(connect) {
				d = (Data)objInputStream.readObject();
				if(d.getMessage1().equals("disconnected")) {
					d.setMessage1("client disconnected!!\n");
					connect = !connect;
				}
				objOutputStream.writeObject(d);
			}
			
			objInputStream.close();
			inputStream.close();
			objOutputStream.close();
			outputStream.close();
			s.close();
		}    
		catch (Exception e) {  }
	}    
}