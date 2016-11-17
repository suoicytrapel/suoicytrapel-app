package lepartycious.daos.implementations;

import java.util.List;

import lepartycious.daos.UserDAO;
import lepartycious.dtos.requestDTOs.UserRequestDTO;
import lepartycious.models.User;
import lepartycious.models.Venue;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User loadUserByUsername(String username) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username.toUpperCase()));
		criteria.add(Restrictions.eq("isActive", true));
		List<User> userList = criteria.list();
		if(CollectionUtils.isEmpty(userList)){
			return null;
		}
		else{
			return userList.get(0);
		}
	}

	@Override
	public void resetPassword(User user) {
		
	}

	@Override
	public void saveOrUpdateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

}
