package lepartycious.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.AddedDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.CityService;
import lepartycious.services.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/v1/search")
public class HomeSearchController {
	
	public static final Long DEFAULT_OFFSET = 0l;
	public static final Long DEFAULT_LIMIT = 10l;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method=RequestMethod.POST, value="/populateList")
	public List<String> loadList(@RequestBody SearchRequestDTO searchRequestDTO) {
		List<String> list = new ArrayList<String>();
		list = commonService.loadList(searchRequestDTO);
		return list;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/city")
	public Map<Long, String> loadCities() {
		Map<Long, String> cityMap = new HashMap<Long, String>();
		cityMap = cityService.loadCities();
		return cityMap;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/city/recentAdditions")
	public Map<String, List<AddedDTO>> getRecentAdditions(@RequestBody Long cityId){
		return commonService.getRecentAdditions(cityId);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public Error handleError(HttpServletRequest req, HttpServletResponse response, Exception exception){
		Error error = new Error();
		error.setErrorMessage(exception.getMessage());
		error.setErrorCode(response.SC_BAD_REQUEST);
		response.setStatus(response.SC_BAD_REQUEST);
		return error;
	}
	
	@ExceptionHandler(Exception.class)
	public Error handleGenericError(HttpServletRequest req, HttpServletResponse response, Exception exception){
		Error error = new Error();
		error.setErrorMessage("Please contact your system administrator");
		error.setErrorCode(response.SC_BAD_REQUEST);
		response.setStatus(response.SC_BAD_REQUEST);
		return error;
	}
	
}
