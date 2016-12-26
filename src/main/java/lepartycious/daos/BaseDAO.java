package lepartycious.daos;


public interface BaseDAO {

	public Long save(Object object);

	public void delete(Object object);
	
	public void update(Object object);
	
	public Object getObjectById(Long id, Class clazz);

}