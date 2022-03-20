import java.io.Serializable;

public class Data implements Serializable {
	private String message1;
	
	public Data(String message1) {
		this.message1 = message1;
	}
	public String getMessage1() {
		return message1;
	}        
	public void setMessage1(String message1) {
		this.message1 = message1;
	}       
}