package lepartycious.dtos.responseDTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lepartycious.dtos.requestDTOs.AdditionalEntityServicesDTO;
import lepartycious.dtos.requestDTOs.EntityFiltersDTO;
import lepartycious.dtos.requestDTOs.EntityServicesDTO;
import lepartycious.dtos.requestDTOs.VenueAmenitiesDTO;
import lepartycious.dtos.requestDTOs.VenueRoomsDTO;

public class LookUpDTO implements Serializable{
	
	//LookUp for Venue
	private List<VenueRoomsDTO> roomLookUpMap;
	private List<EntityFiltersDTO> estTypeLookUp;
	private List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap;
	private List<EntityServicesDTO> basicServiceLookUpMap;
	private List<EntityServicesDTO> additionalServiceLookUpMap;
	private List<EntityServicesDTO> policyServiceLookUp;
	private List<VenueAmenitiesDTO> cateringDecorAmenityLookUp;
	private List<EntityServicesDTO> basicCateringServicesLookUp;
	private List<EntityServicesDTO> cuisinesLookUp;
	
	//LookUp for Caterer
	private List<EntityServicesDTO> catererServicesLookUp;
	private List<EntityFiltersDTO> catererPriceLookUp;
	private List<EntityFiltersDTO> catererCapacitylookUp;
	private List<EntityFiltersDTO> catererTypeLookUp;
	
	//LookUp for Entertainment
	private List<EntityFiltersDTO> entertainmentType;
	private List<EntityServicesDTO> entertainmentServicesLookUp;
	
	
	//LookUp for Decorator
	private List<EntityServicesDTO> decoratorServicesLookUp;
	private List<EntityFiltersDTO> decoratorPriceLookUp;
	
	
	//LookUp for Photographer
	private List<EntityServicesDTO> photographerServicesLookUp;
	private List<EntityFiltersDTO> photographerPriceLookUp;
	private List<EntityFiltersDTO> photographerTypeLookUp;
	
	//LookUp for Others
	private List<EntityFiltersDTO> othersTypeLookUp;
	private List<EntityServicesDTO> othersServicesLookUp;
	
	private List<EntityFiltersDTO> eventTypeLookUp;
	
	public List<EntityFiltersDTO> getEventTypeLookUp() {
		return eventTypeLookUp;
	}
	public void setEventTypeLookUp(List<EntityFiltersDTO> eventTypeLookUp) {
		this.eventTypeLookUp = eventTypeLookUp;
	}
	public List<VenueRoomsDTO> getRoomLookUpMap() {
		return roomLookUpMap;
	}
	public void setRoomLookUpMap(List<VenueRoomsDTO> roomLookUpMap) {
		this.roomLookUpMap = roomLookUpMap;
	}
	public List<VenueAmenitiesDTO> getEventAreaAmenityLookUpMap() {
		return eventAreaAmenityLookUpMap;
	}
	public void setEventAreaAmenityLookUpMap(
			List<VenueAmenitiesDTO> eventAreaAmenityLookUpMap) {
		this.eventAreaAmenityLookUpMap = eventAreaAmenityLookUpMap;
	}
	public List<EntityServicesDTO> getBasicServiceLookUpMap() {
		return basicServiceLookUpMap;
	}
	public void setBasicServiceLookUpMap(
			List<EntityServicesDTO> basicServiceLookUpMap) {
		this.basicServiceLookUpMap = basicServiceLookUpMap;
	}
	public List<EntityServicesDTO> getAdditionalServiceLookUpMap() {
		return additionalServiceLookUpMap;
	}
	public void setAdditionalServiceLookUpMap(
			List<EntityServicesDTO> additionalServiceLookUpMap) {
		this.additionalServiceLookUpMap = additionalServiceLookUpMap;
	}
	public List<EntityServicesDTO> getPolicyServiceLookUp() {
		return policyServiceLookUp;
	}
	public void setPolicyServiceLookUp(List<EntityServicesDTO> policyServiceLookUp) {
		this.policyServiceLookUp = policyServiceLookUp;
	}
	public List<VenueAmenitiesDTO> getCateringDecorAmenityLookUp() {
		return cateringDecorAmenityLookUp;
	}
	public void setCateringDecorAmenityLookUp(
			List<VenueAmenitiesDTO> cateringDecorAmenityLookUp) {
		this.cateringDecorAmenityLookUp = cateringDecorAmenityLookUp;
	}
	public List<EntityServicesDTO> getBasicCateringServicesLookUp() {
		return basicCateringServicesLookUp;
	}
	public void setBasicCateringServicesLookUp(
			List<EntityServicesDTO> basicCateringServicesLookUp) {
		this.basicCateringServicesLookUp = basicCateringServicesLookUp;
	}
	public List<EntityServicesDTO> getCuisinesLookUp() {
		return cuisinesLookUp;
	}
	public void setCuisinesLookUp(List<EntityServicesDTO> cuisinesLookUp) {
		this.cuisinesLookUp = cuisinesLookUp;
	}
	public List<EntityServicesDTO> getCatererServicesLookUp() {
		return catererServicesLookUp;
	}
	public void setCatererServicesLookUp(
			List<EntityServicesDTO> catererServicesLookUp) {
		this.catererServicesLookUp = catererServicesLookUp;
	}
	public List<EntityFiltersDTO> getCatererPriceLookUp() {
		return catererPriceLookUp;
	}
	public void setCatererPriceLookUp(List<EntityFiltersDTO> catererPriceLookUp) {
		this.catererPriceLookUp = catererPriceLookUp;
	}
	public List<EntityFiltersDTO> getCatererCapacitylookUp() {
		return catererCapacitylookUp;
	}
	public void setCatererCapacitylookUp(
			List<EntityFiltersDTO> catererCapacitylookUp) {
		this.catererCapacitylookUp = catererCapacitylookUp;
	}
	public List<EntityFiltersDTO> getCatererTypeLookUp() {
		return catererTypeLookUp;
	}
	public void setCatererTypeLookUp(List<EntityFiltersDTO> catererTypeLookUp) {
		this.catererTypeLookUp = catererTypeLookUp;
	}
	public List<EntityFiltersDTO> getEntertainmentType() {
		return entertainmentType;
	}
	public void setEntertainmentType(List<EntityFiltersDTO> entertainmentType) {
		this.entertainmentType = entertainmentType;
	}
	public List<EntityServicesDTO> getEntertainmentServicesLookUp() {
		return entertainmentServicesLookUp;
	}
	public void setEntertainmentServicesLookUp(
			List<EntityServicesDTO> entertainmentServicesLookUp) {
		this.entertainmentServicesLookUp = entertainmentServicesLookUp;
	}
	public List<EntityServicesDTO> getDecoratorServicesLookUp() {
		return decoratorServicesLookUp;
	}
	public void setDecoratorServicesLookUp(
			List<EntityServicesDTO> decoratorServicesLookUp) {
		this.decoratorServicesLookUp = decoratorServicesLookUp;
	}
	public List<EntityFiltersDTO> getDecoratorPriceLookUp() {
		return decoratorPriceLookUp;
	}
	public void setDecoratorPriceLookUp(List<EntityFiltersDTO> decoratorPriceLookUp) {
		this.decoratorPriceLookUp = decoratorPriceLookUp;
	}
	public List<EntityServicesDTO> getPhotographerServicesLookUp() {
		return photographerServicesLookUp;
	}
	public void setPhotographerServicesLookUp(
			List<EntityServicesDTO> photographerServicesLookUp) {
		this.photographerServicesLookUp = photographerServicesLookUp;
	}
	public List<EntityFiltersDTO> getPhotographerPriceLookUp() {
		return photographerPriceLookUp;
	}
	public void setPhotographerPriceLookUp(
			List<EntityFiltersDTO> photographerPriceLookUp) {
		this.photographerPriceLookUp = photographerPriceLookUp;
	}
	public List<EntityFiltersDTO> getPhotographerTypeLookUp() {
		return photographerTypeLookUp;
	}
	public void setPhotographerTypeLookUp(
			List<EntityFiltersDTO> photographerTypeLookUp) {
		this.photographerTypeLookUp = photographerTypeLookUp;
	}
	public List<EntityFiltersDTO> getOthersTypeLookUp() {
		return othersTypeLookUp;
	}
	public void setOthersTypeLookUp(List<EntityFiltersDTO> othersTypeLookUp) {
		this.othersTypeLookUp = othersTypeLookUp;
	}
	public List<EntityServicesDTO> getOthersServicesLookUp() {
		return othersServicesLookUp;
	}
	public void setOthersServicesLookUp(List<EntityServicesDTO> othersServicesLookUp) {
		this.othersServicesLookUp = othersServicesLookUp;
	}
	public List<EntityFiltersDTO> getEstTypeLookUp() {
		return estTypeLookUp;
	}
	public void setEstTypeLookUp(List<EntityFiltersDTO> estTypeLookUp) {
		this.estTypeLookUp = estTypeLookUp;
	}
}
