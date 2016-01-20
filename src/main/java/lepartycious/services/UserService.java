package lepartycious.services;

import java.util.List;

import lepartycious.models.Caterer;
import lepartycious.models.Venue;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly=true)
public interface UserService{

	public List<Venue> getVenue();

	public List<Caterer> getCaterer();
}