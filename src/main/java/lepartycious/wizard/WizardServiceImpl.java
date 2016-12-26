package lepartycious.wizard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lepartycious.daos.CommonDAO;
import lepartycious.models.Locality;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class WizardServiceImpl implements WizardService {

	@Autowired
	private CommonDAO commonDAO;

	@Override
	public Map<Long, String> getLocalities(Long cityId) throws Exception{
		Map<Long, String> localityMap = new HashMap<Long, String>();
		List<Locality> localities = commonDAO.getLocalities(cityId);
		for(Locality locality : localities){
			localityMap.put(locality.getLocalityId(), locality.getName());
		}
		return localityMap;
	}


}
