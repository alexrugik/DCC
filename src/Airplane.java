package myproject;

import org.jgroups.JChannel;
import org.jgroups.Message;
import com.google.gson.Gson;

public class Airplane {

	private String codeName;
	private static int counterId = -1;
	private int id;
	private String type;
	private int latitude;
	private int longitude;
	private int altitude;
	private String direction;
	JChannel channel;
	private String kindOf = "airplane";

	public Airplane(String type, String codeName) {
		this.type = type;
		this.codeName = codeName;
		this.id = counterId--;
	}

	private Airplane(String codeName, String type, String direction,
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
		channel.setName("airplanes");
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
		String toJson = new Gson().toJson(new Airplane(this.codeName,
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

	public static void main(String args[]) throws Exception {

		Airplane bettaA = new Airplane("L39", "BettaA");
		bettaA.setStartPosition("W", 7, 5, 200);

		Airplane bettaB = new Airplane("A300", "BettaA");
		bettaB.setStartPosition("W", 7, 5, 200);

		Airplane bettaC = new Airplane("B777", "BettaA");
		bettaC.setStartPosition("W", 7, 5, 200);

		bettaA.move("W", 2, 2, 3);
		bettaB.move("S", 3, 3, 2);
		bettaC.move("E", 4, 4, 1);
		  
		Thread.sleep(1000);
		
		bettaA.move("W", 7, 5, 3);
		bettaB.move("S", 3, 9, 2);
		bettaC.move("E", 1, 15, 10);
		
		Thread.sleep(1000);
		
		bettaA.move("W", 3, 2, 3);
		bettaB.move("S", 7, 4, 8);
		bettaC.move("E", 3, 2, 9);
		
		Thread.sleep(1000);

		bettaA.move("W", 1, 2, 7);
		bettaB.move("S", 4, 3, 4);
		bettaC.move("E", 8, 2, 2);
		
		
		Thread.sleep(1000);

		bettaA.move("W", 1, 2, 7);
		bettaB.move("S", 4, 3, 4);
		bettaC.move("E", 8, 2, 2);
		
		

	}

}
