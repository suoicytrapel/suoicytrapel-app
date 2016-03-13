package lepartycious.dtos.requestDTOs;

import java.io.Serializable;
import java.util.List;

public class FilterWrapperDTO implements Serializable{
	
	private List<Long> serviceList;
	private List<Long> localityList;
	private List<Long> amenityList;
	private List<Long> priceRangeList;
	private List<Long> roomList;
	private List<Long> estList;
	private List<Long> eventList;
	private List<Long> rentalList;
	private List<Long> othersList;
	
	public List<Long> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Long> serviceList) {
		this.serviceList = serviceList;
	}
	public List<Long> getLocalityList() {
		return localityList;
	}
	public void setLocalityList(List<Long> localityList) {
		this.localityList = localityList;
	}
	public List<Long> getAmenityList() {
		return amenityList;
	}
	public void setAmenityList(List<Long> amenityList) {
		this.amenityList = amenityList;
	}
	public List<Long> getPriceRangeList() {
		return priceRangeList;
	}
	public void setPriceRangeList(List<Long> priceRangeList) {
		this.priceRangeList = priceRangeList;
	}
	public List<Long> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<Long> roomList) {
		this.roomList = roomList;
	}
	public List<Long> getEstList() {
		return estList;
	}
	public void setEstList(List<Long> estList) {
		this.estList = estList;
	}
	public List<Long> getEventList() {
		return eventList;
	}
	public void setEventList(List<Long> eventList) {
		this.eventList = eventList;
	}
	public List<Long> getRentalList() {
		return rentalList;
	}
	public void setRentalList(List<Long> rentalList) {
		this.rentalList = rentalList;
	}
	public List<Long> getOthersList() {
		return othersList;
	}
	public void setOthersList(List<Long> othersList) {
		this.othersList = othersList;
	}
	
}
