package RestApiAssignment.RestApi.Prediction;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class output {
 private String date;
 public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public float getTmax() {
	return tmax;
}
public void setTmax(float tmax) {
	this.tmax = tmax;
}
public float getTmin() {
	return tmin;
}
public void setTmin(float tmin) {
	this.tmin = tmin;
}
private float tmax;
 private float tmin;
 public output(){
	 
 }
 public output(String date,float tmax,float tmin){
	 this.date=date;
	 this.tmax=tmax;
	 this.tmin=tmin;
 }
}
