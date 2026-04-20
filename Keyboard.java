
public class Keyboard extends Product{
	private String type;
	private String layout;
	
	// constructor
	public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity, 
			double retailPrice, ProductCategory category, String type, String layout) {
		super(barcode, brand, color, connectivity, retailPrice, category);
		this.type = type;
		this.layout = layout;
	}
	
	// getter
	public String getType() {
		return type; 
		}
	
    public String getLayout() { 
    	return layout; 
    	}
	
	@Override
	public String toString() {
		return String.format("%d, %s, %s, %s, %s, %s, %f, %s", 
	            getBarcode(), getCategory(), getType(), getBrand(), getColor(), getConnectivity(), getRetailPrice(), layout);
	}
	
	
}
