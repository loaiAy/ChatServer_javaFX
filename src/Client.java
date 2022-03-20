
public class Client {
	private String computerName;
	private String port;
	private Data d;
	
	
	public Client(String name, String port, Data d) {
		this.computerName = name;
		this.port = port;
		this.setD(d);
		
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Data getD() {
		return d;
	}

	public void setD(Data d) {
		this.d = d;
	}
}
