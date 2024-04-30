package core;

public class City {
	private int city_id;
	private String city_name;
	
	// Default constructor
    public City() {}

    // Parameterized constructor
    public City(int city_id, String cityName) {
        this.setCity_name(cityName);
    }

    //getter and setter
	public int getCity_id() {
		return city_id;
	}

	
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
    
}
