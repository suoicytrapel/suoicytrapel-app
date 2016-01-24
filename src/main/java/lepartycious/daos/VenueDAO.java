package lepartycious.daos;

import java.util.List;

import lepartycious.models.Venue;

public interface VenueDAO extends BaseDAO{
	
	public List<Venue> getVenues(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Venue> loadVenueList(Long cityId, String searchString);

	public Long getVenueCount(Long cityid, String searchString);


}
