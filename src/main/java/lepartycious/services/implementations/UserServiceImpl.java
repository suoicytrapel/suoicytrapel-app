package lepartycious.services.implementations;

import java.util.ArrayList;
import java.util.List;

import lepartycious.daos.UserDAO;
import lepartycious.models.Caterer;
import lepartycious.models.Venue;
import lepartycious.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<Venue> getVenue() {
		List<Venue> v = new ArrayList<Venue>();
		v = userDAO.getVenue();
		System.out.println("size is:" + v.get(0).getAttachments());
		return v;
	}

	@Override
	public List<Caterer> getCaterer() {
		List<Caterer> c = new ArrayList<Caterer>();
		c = userDAO.getCaterer();
		System.out.println("size is:" + c.get(0).getAttachments());
		return c;
	}

}
