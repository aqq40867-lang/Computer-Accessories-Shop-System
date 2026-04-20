
public class Mouse extends Product{
	private String type;
    private int numOfButtons;
    
    // constructor
    public Mouse(int barcode, String brand, String color, ConnectivityType connectivity, 
            double retailPrice, ProductCategory category, String deviceType, int numberOfButtons) {
    	super(barcode, brand, color, connectivity, retailPrice, category);
		this.type = deviceType;
		this.numOfButtons = numberOfButtons;
    }
    
    // getter
	public String getType() {
		return type; 
		}
	
    public int getNumOfButtons() { 
    	return numOfButtons; 
    	}
    
    
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s, %f, %d", 
                             getBarcode(), getCategory(), getType(), getBrand(), getColor(), getConnectivity(), getRetailPrice(), getNumOfButtons());
    }
		   
}
