package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.Map;

public class VenuePackageDTO implements Serializable{
	
	private String packageType;
	private String packagePrice;
	private Map<String, String> packageItems;
	
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(String packagePrice) {
		this.packagePrice = packagePrice;
	}
	public Map<String, String> getPackageItems() {
		return packageItems;
	}
	public void setPackageItems(Map<String, String> packageItems) {
		this.packageItems = packageItems;
	}
	
}
