package lepartycious.daos;

import java.util.List;

import lepartycious.models.Caterer;
import lepartycious.models.Venue;

public interface UserDAO extends BaseDAO{
	
	public List<Venue> getVenue();
	
	public List<Caterer> getCaterer();
	
}
