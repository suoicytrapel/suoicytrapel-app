package lepartycious.daos;

import java.util.List;

import lepartycious.models.Decorator;

public interface DecoratorDAO extends BaseDAO{
	
	public List<Decorator> getDecorators(Long cityId, String searchString, Long offset, Long limit, String sortField, String sortOrder);

	public List<Decorator> loadDecoratorList(Long cityId, String searchString);

	public Long getDecoratorCount(Long cityid, String searchString);

	public Decorator fetchDecoratorDetails(Long cityId, String name);

}
