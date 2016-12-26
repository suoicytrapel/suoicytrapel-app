package lepartycious.controllers.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.DataRequestDTO;
import lepartycious.dtos.requestDTOs.FilterRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.DetailResponseDTO;
import lepartycious.dtos.responseDTOs.FilterResponseWrapperDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTO;
import lepartycious.dtos.responseDTOs.SearchResponseDTOWrapper;
import lepartycious.services.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/rest/v1/fetch")
public class DataController {

	public static final Long DEFAULT_OFFSET = 0l;
	public static final Long DEFAULT_LIMIT = 10l;

	@Autowired
	private CommonService commonService;

	@RequestMapping(method=RequestMethod.POST)
	public SearchResponseDTOWrapper fetchResults(@RequestBody SearchRequestDTO searchRequestDTO) {
		if(searchRequestDTO.getLimit() == null){
			searchRequestDTO.setLimit(DEFAULT_LIMIT);
		}
		Long offset = (searchRequestDTO.getOffset()-1)*searchRequestDTO.getLimit();
		searchRequestDTO.setOffset(offset);
		SearchResponseDTOWrapper searchResponseDTOWrapper = commonService.getEntities(searchRequestDTO);
		return searchResponseDTOWrapper;
	}

	@RequestMapping(method=RequestMethod.POST, value="/details")
	public DetailResponseDTO fetchDetails(@RequestBody DataRequestDTO dataRequestDTO) {
		DetailResponseDTO detailResponseDTO = commonService.fetchDetails(dataRequestDTO);
		List<SearchResponseDTO> recommendationList = commonService.fetchRecomendations(dataRequestDTO.getSearchType(), dataRequestDTO.getCityId());
		detailResponseDTO.setRecommendationList(recommendationList);
		return detailResponseDTO;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/filters")
	public FilterResponseWrapperDTO fetchFilters(@RequestBody FilterRequestDTO filterRequestDTO) {
		FilterResponseWrapperDTO filterResponseDTO = new FilterResponseWrapperDTO();
		filterResponseDTO = commonService.loadFilters(filterRequestDTO.getSearchType(), filterRequestDTO.getCityId());
		return filterResponseDTO;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pushData")
	public Map<String, String> pushDataToDatabase(@RequestBody String query){
		return commonService.pushDataToDatabase(query);
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
