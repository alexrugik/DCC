package myproject;

import org.jgroups.JChannel;
import org.jgroups.Message;
import com.google.gson.Gson;

public class Helicopter {

	private String codeName;
	private static int counterId = 0;
	private int id;
	private String type;
	private int latitude;
	private int longitude;
	private int altitude;
	private String direction;
	private JChannel channel;
	private String kindOf = "helicopter";

	public Helicopter(String type, String codeName) {
		this.type = type;
		this.codeName = codeName;
		this.id = counterId++;
	}

	private Helicopter(String codeName, String type, String direction,
			int latitude, int longitude, int altitude, int id, String kindOf) {
		this.type = type;
		this.codeName = codeName;
		this.direction = direction;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.id = id;
		this.kindOf = kindOf;
	}

	public void move(String moveDirection, int moveLatitude, int moveLongitude,
			int moveAltitude) throws Exception {
		this.latitude = this.latitude + moveLatitude;
		this.longitude = this.longitude + moveLongitude;
		this.altitude = this.altitude + moveAltitude;
		this.direction = moveDirection;
		this.sendCurrentPosition();
	}

	public void sendCurrentPosition() throws Exception {
		channel = new JChannel();
		channel.setName("helicopters");
		channel.connect("cluster");
		channel.send(new Message(null, null, toJson()));
		channel.close();
	}

	public void setStartPosition(String direction, int latitude, int longitude,
			int altitude) {
		this.direction = direction;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public String toJson() {
		String toJson = new Gson().toJson(new Helicopter(this.codeName,
				this.type, this.direction, this.latitude, this.longitude,
				this.altitude, this.id, this.kindOf));
		return toJson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public String getKindOf() {
		return this.kindOf;
	}

	public void setKindOf(String kindOf) {
		this.kindOf = kindOf;
	}

	public static void main(String args[]) throws Exception {

		Helicopter alfaA = new Helicopter("Mi-17", "AlfaA");
		alfaA.setStartPosition("W", 1, 2, 100);

		Helicopter alfaB = new Helicopter("Mi-171", "AlfaB");
		alfaB.setStartPosition("S", 3, 4, 110);

		Helicopter alfaC = new Helicopter("Mi-2", "AlfaC");
		alfaC.setStartPosition("E", 1, 6, 130);

		
		  alfaA.move("W", 1, 1, 1); 
		  alfaB.move("S", 2, 2, 2); 
		  alfaC.move("N", 4, 2, 9);
		 
		  Thread.sleep(1000);
		  
		  alfaA.move("W", 4, 1, 15); 
		  alfaB.move("S", 2, 2, 4); 
		  alfaC.move("N",3, 3, 3);
		 
		  
		  Thread.sleep(1000);
		  
		  alfaA.move("W", 7, 1, 10); 
		  alfaB.move("S", 4, 15, 1); 
		  alfaC.move("N", 5, 5, 5);

		  Thread.sleep(1000);
		  
		  alfaA.move("W", 3, 2, 9); 
		  alfaB.move("S", 10, 1, 1); 
		  alfaC.move("N", 4, -3, -3);
	}

}
