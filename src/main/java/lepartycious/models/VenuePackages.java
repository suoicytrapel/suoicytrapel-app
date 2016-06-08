package lepartycious.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="venue_packages")

public class VenuePackages implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14656908523098773L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long venuePackageId;
	
	@Column(name="welcome_drinks")
	private String welcomeDrinks;
	
	@Column(name="soups")
	private String soups;
	
	@Column(name="salads")
	private String salads;
	
	@Column(name="veg_starters")
	private String vegStarters;
	
	@Column(name="non_veg_starters")
	private String nonVegStarters;
	
	@Column(name="veg_main_course")
	private String vegMainCourse;
	
	@Column(name="nonveg_main_course")
	private String nonvegMainCourse;
	
	@Column(name="rice_biryani")
	private String riceBiryani;
	
	@Column(name="assorted_breads")
	private String assortedBreads;
	
	@Column(name="raita")
	private String raita;
	
	@Column(name="dal")
	private String dal;
	
	@Column(name="deserts")
	private String deserts;
	
	@Column(name="package_type")
	private String packageType;
	
	@Column(name="package_price")
	private String packagePrice;
	
	@Column(name="added_on")
	private Date addedOn;
	
	@Column(name="venue_id")
	private Long venue;

	public long getVenuePackageId() {
		return venuePackageId;
	}

	public void setVenuePackageId(long venuePackageId) {
		this.venuePackageId = venuePackageId;
	}

	public String getWelcomeDrinks() {
		return welcomeDrinks;
	}

	public void setWelcomeDrinks(String welcomeDrinks) {
		this.welcomeDrinks = welcomeDrinks;
	}

	public String getSoups() {
		return soups;
	}

	public void setSoups(String soups) {
		this.soups = soups;
	}

	public String getSalads() {
		return salads;
	}

	public void setSalads(String salads) {
		this.salads = salads;
	}

	public String getVegStarters() {
		return vegStarters;
	}

	public void setVegStarters(String vegStarters) {
		this.vegStarters = vegStarters;
	}

	public String getNonVegStarters() {
		return nonVegStarters;
	}

	public void setNonVegStarters(String nonVegStarters) {
		this.nonVegStarters = nonVegStarters;
	}

	public String getVegMainCourse() {
		return vegMainCourse;
	}

	public void setVegMainCourse(String vegMainCourse) {
		this.vegMainCourse = vegMainCourse;
	}

	public String getNonvegMainCourse() {
		return nonvegMainCourse;
	}

	public void setNonvegMainCourse(String nonvegMainCourse) {
		this.nonvegMainCourse = nonvegMainCourse;
	}

	public String getRiceBiryani() {
		return riceBiryani;
	}

	public void setRiceBiryani(String riceBiryani) {
		this.riceBiryani = riceBiryani;
	}

	public String getAssortedBreads() {
		return assortedBreads;
	}

	public void setAssortedBreads(String assortedBreads) {
		this.assortedBreads = assortedBreads;
	}

	public String getRaita() {
		return raita;
	}

	public void setRaita(String raita) {
		this.raita = raita;
	}

	public String getDal() {
		return dal;
	}

	public void setDal(String dal) {
		this.dal = dal;
	}

	public String getDeserts() {
		return deserts;
	}

	public void setDeserts(String deserts) {
		this.deserts = deserts;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Long getVenue() {
		return venue;
	}

	public void setVenue(Long venue) {
		this.venue = venue;
	}

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
}
