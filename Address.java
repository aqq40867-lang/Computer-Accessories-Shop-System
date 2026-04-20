
public class Address {
	private String houseNum;
    private String postcode;
    private String city;
    
    // constructor
    public Address(String houseNum, String postcode, String city) {
    	this.houseNum = houseNum;
    	this.postcode = postcode;
    	this.city = city;
    }
    
    // setter
    public String getHouseNum() {
    	return houseNum;
    }
    
    public String getPostcode() {
    	return postcode;
    }
    public String getCity() {
    	return city;
    }
    
    @Override
    public String toString() {
        return getHouseNum() + ", " + getPostcode() + ", " + getCity();
    }

}
