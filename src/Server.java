import java.io.IOException;
import java.net.*;

public class Server {
	
	public Server(int count) {
		ServerSocket sc = null;
		Socket s = null;
		
		try {
			sc = new ServerSocket(7777);
			while(true) {
				s = sc.accept();
				count++;
					try {
						//if there two accepted clients , start the server
						if(count>=2) {
							new ServerThread(s);
						}
					} catch (Exception e) {
						e.printStackTrace();
						}
				} 
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try {
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	public static void main(String[] args) {
		new Server(0);
	}
}
