package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="city")
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14232328521309090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cityId;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="state")
	private String state;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="city")
	private List<Venue> venues;
	
	@OneToMany(mappedBy="city")
	private List<Caterer> caterers;
	
	@OneToMany(mappedBy="city")
	private List<Photographer> photographers;
	
	@OneToMany(mappedBy="city")
	private List<Decorator> decorators;
	
	@OneToMany(mappedBy="city")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy="city")
	private List<Band> bands;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public List<Caterer> getCaterers() {
		return caterers;
	}

	public void setCaterers(List<Caterer> caterers) {
		this.caterers = caterers;
	}

	public List<Photographer> getPhotographers() {
		return photographers;
	}

	public void setPhotographers(List<Photographer> photographers) {
		this.photographers = photographers;
	}

	public List<Decorator> getDecorators() {
		return decorators;
	}

	public void setDecorators(List<Decorator> decorators) {
		this.decorators = decorators;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

}
