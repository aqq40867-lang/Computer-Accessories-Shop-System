
public abstract class Product {
	// Defining Private Attributes
	private int barcode;
	private String brand;
	private String color;
	private ConnectivityType connectivity; 
	private double retailPrice;
	private ProductCategory category;
	
	// Constructor
	public Product(int barcode, String brand, String color, ConnectivityType connectivity, 
			double retailPrice, ProductCategory category) {
		this.barcode = barcode;
		this.barcode = barcode; 
		this.brand = brand;
		this.color = color;
		this.connectivity = connectivity;
		this.retailPrice = retailPrice;
		this.category = category;
	}
	
	// Getter
	public int getBarcode() {
		return barcode;
	}
	public String getBrand() {
		return brand;
	}
	public String getColor() {
		return color;
	}
	public ConnectivityType getConnectivity() {
		return connectivity;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public ProductCategory getCategory() {
		return category;
	}
	
	// Abstract Method Definition
	public abstract String toString();

}
