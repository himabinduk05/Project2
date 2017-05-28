package RestApiAssignment.RestApi.Prediction;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DateOutput {
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DateOutput(String date) {
		super();
		this.date = date;
	}
	public DateOutput (){
		 
	 }
}
