package Lyft;

/**
 * Represents a point as a longitude, latitude pair 
 * @author Ankit Patel
 *
 */
public class Point{
	
	private double latitude;
	private double longitude;
	private String name;
	
	public Point(double latitude, double longitude, String name){
		this.latitude = latitude;
		this.longitude = longitude;	
		this.name = name;
	}
	
	public void setLongitude(double newLongitude){
		this.longitude = newLongitude;
	}
	
	public void setLatitude(double newLatitude){
		this.latitude = newLatitude;
	}
	
	public void setName(String newName){
		this.name = newName;
	}
	
	public double getLongitude(){
		return this.longitude;
	}
	
	public double getLatitude(){
		return this.latitude;
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.name + ": <" + this.latitude + "," + this.longitude + ">");
		return sb.toString();
	}
	
}