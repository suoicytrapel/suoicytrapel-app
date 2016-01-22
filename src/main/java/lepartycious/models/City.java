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

@Entity
@Table(name="city")
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14232328521309090L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long city_id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="state")
	private String state;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@OneToMany(mappedBy="cityId")
	private List<Venue> venues;
	
	@OneToMany(mappedBy="cityId")
	private List<Caterer> caterers;
	
	@OneToMany(mappedBy="cityId")
	private List<Photographer> photographers;
	
	@OneToMany(mappedBy="cityId")
	private List<Decorator> decorators;
	
	@OneToMany(mappedBy="cityId")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy="cityId")
	private List<Band> bands;

}
