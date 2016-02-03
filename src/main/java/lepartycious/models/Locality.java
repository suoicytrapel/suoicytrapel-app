package lepartycious.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="locality")
public class Locality implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 14326788534129090L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long localityId;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "added_on")
	private Date addedOn;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	public long getLocalityId() {
		return localityId;
	}

	public void setLocalityId(long localityId) {
		this.localityId = localityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
