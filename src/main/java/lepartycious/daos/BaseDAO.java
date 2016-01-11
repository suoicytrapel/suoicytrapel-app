package lepartycious.daos;


public interface BaseDAO {

	public void save(Object object);

	public void delete(Object object);
	
	public void update(Object object);
	
	public void getObjectById(Long id);

}