package lepartycious.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.VenueDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest/v1/data")
public class DataInsertionController {
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/create/venue", method=RequestMethod.POST)
	public void createVenue(@RequestBody VenueDTO venueDTO){
		//TODO Service call to save venue details
	}
	
	@RequestMapping(value="/ifAlreadyExists/{enitytType}/{entityName}", method=RequestMethod.GET)
	public Boolean checkIfExistingEntity(@RequestParam String enitytType, @RequestParam String entityName){
		return false;
	}
	
	@RequestMapping(value="/delete/{enitytType}/{entityId}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void deleteEntity(@RequestParam String enitytType, @RequestParam Long entityId){
		//TODO
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
