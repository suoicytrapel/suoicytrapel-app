package lepartycious.daos;

import java.util.List;

import lepartycious.models.Band;

public interface BandDAO extends BaseDAO{

	public List<Band> getBands(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Band> loadBandList(Long cityId, String searchString);

	public Long getBandCount(Long cityid, String searchString);

	public Band fetchBandDetails(Long cityId, String name);
}
