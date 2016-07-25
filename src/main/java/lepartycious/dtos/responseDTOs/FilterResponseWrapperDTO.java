package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;

public class FilterResponseWrapperDTO implements Serializable{
	
	private List<FilterResponseDTO> services;
	private List<FilterResponseDTO> amenities;
	private List<FilterResponseDTO> rooms;
	private List<FilterResponseDTO> localities;
	private List<FilterResponseDTO> establishments;
	private List<FilterResponseDTO> priceRange;
	private List<FilterResponseDTO> entertainmentType;
	private List<FilterResponseDTO> eventType;
	private List<FilterResponseDTO> othersType;
	private List<FilterResponseDTO> capacity;
	private List<FilterResponseDTO> catererType;
	private List<FilterResponseDTO> photographerType;
	
	public List<FilterResponseDTO> getServices() {
		return services;
	}
	public void setServices(List<FilterResponseDTO> services) {
		this.services = services;
	}
	public List<FilterResponseDTO> getAmenities() {
		return amenities;
	}
	public void setAmenities(List<FilterResponseDTO> amenities) {
		this.amenities = amenities;
	}
	public List<FilterResponseDTO> getRooms() {
		return rooms;
	}
	public void setRooms(List<FilterResponseDTO> rooms) {
		this.rooms = rooms;
	}
	public List<FilterResponseDTO> getLocalities() {
		return localities;
	}
	public void setLocalities(List<FilterResponseDTO> localities) {
		this.localities = localities;
	}
	public List<FilterResponseDTO> getEstablishments() {
		return establishments;
	}
	public void setEstablishments(List<FilterResponseDTO> establishments) {
		this.establishments = establishments;
	}
	public List<FilterResponseDTO> getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(List<FilterResponseDTO> priceRange) {
		this.priceRange = priceRange;
	}
	public List<FilterResponseDTO> getEntertainmentType() {
		return entertainmentType;
	}
	public void setEntertainmentType(List<FilterResponseDTO> entertainmentType) {
		this.entertainmentType = entertainmentType;
	}
	public List<FilterResponseDTO> getEventType() {
		return eventType;
	}
	public void setEventType(List<FilterResponseDTO> eventType) {
		this.eventType = eventType;
	}
	public List<FilterResponseDTO> getOthersType() {
		return othersType;
	}
	public void setOthersType(List<FilterResponseDTO> othersType) {
		this.othersType = othersType;
	}
	public List<FilterResponseDTO> getCapacity() {
		return capacity;
	}
	public void setCapacity(List<FilterResponseDTO> capacity) {
		this.capacity = capacity;
	}
	public List<FilterResponseDTO> getCatererType() {
		return catererType;
	}
	public void setCatererType(List<FilterResponseDTO> catererType) {
		this.catererType = catererType;
	}
	public List<FilterResponseDTO> getPhotographerType() {
		return photographerType;
	}
	public void setPhotographerType(List<FilterResponseDTO> photographerType) {
		this.photographerType = photographerType;
	}
}
