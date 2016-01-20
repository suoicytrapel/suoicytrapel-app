package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.UserDAO;
import lepartycious.models.Caterer;
import lepartycious.models.Venue;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Venue> getVenue() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Venue.class);
		List ls =  criteria.list();
		return ls;
	}

	@Override
	public List<Caterer> getCaterer() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Caterer.class);
		List ls =  criteria.list();
		return ls;
	}

}
