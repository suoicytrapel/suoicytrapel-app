package lepartycious.dtos.requestDTOs;

import java.io.Serializable;

public class VenuePackagesDTO implements Serializable{
	
	private String welcomeDrinks;
	private String soups;
	private String salads;
	private String vegStarters;
	private String nonVegStarters;
	private String vegMainCourse;
	private String nonvegMainCourse;
	private String riceBiryani;
	private String assortedBreads;
	private String raita;
	private String dal;
	private String deserts;
	private String packageType;
	private String packagePrice;
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
