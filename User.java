
public abstract class User {
	private int userID;
    private String username;
    private String name;
    private Address address;
    
    // constructor
    public User(int userID, String username, String name, Address address) {
    	this.userID = userID;
    	this.username = username;
    	this.name = name;
    	this.address = address;
    }
    
    // getter
    public int getUserID() {
    	return userID;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public String getName() {
    	return name;
    }
    
    public Address getAddress() {
    	return address;
    }
    
    

}
