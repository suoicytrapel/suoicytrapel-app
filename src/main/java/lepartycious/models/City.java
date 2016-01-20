/*package lepartycious.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long city_id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="state")
	private String state;
	
	@Column(name = "added_on")
	private Date addedOn;

}
*/