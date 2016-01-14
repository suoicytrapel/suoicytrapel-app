package lepartycious.daos.implementations;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lepartycious.daos.UserDAO;
import lepartycious.models.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User loadUserByUsername(String username) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getDataClass());
		criteria.add(Restrictions.eq("email", username));
		User user = (User) criteria.list().get(0);
		return user;
	}

	private Class<User> getDataClass(){
		return User.class;
	}

	@Override
	public Optional<User> findOneByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
