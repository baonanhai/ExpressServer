package controllers;


public class ExpresstationInfo {
	/**已揽件*/
	public static final int state_accept = 0;
	/**快件已从小站取出，在入库过程中*/
	public static final int state_ = 1;
	/**快件出库，在去小站过程中*/
	public static final int state_despatch = 2;
	/**快件已进入小站，等待取件*/
	public static final int state_in_station = 3;
	/**快件已经被取走*/
	public static final int state_take = 4;
	
	private String id;
	private String time;
	private String weight;
	private String toPerson;
	private String toLocation;
	private String toMobile;
	private String fromPerson;
	private String fromLocation;
	private String fromMobile;
	private String price;
	private int state = state_accept;

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getToPerson() {
		return toPerson;
	}

	public void setToPerson(String toPerson) {
		this.toPerson = toPerson;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	public String getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(String fromPerson) {
		this.fromPerson = fromPerson;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getFromMobile() {
		return fromMobile;
	}

	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}