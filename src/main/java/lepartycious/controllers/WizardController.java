package lepartycious.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.dtos.responseDTOs.WizardDTO;
import lepartycious.services.WizardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/secured/v1")
public class WizardController {
	
	@Autowired
	private WizardService wizardService;

	@RequestMapping(value="/wizard/venue/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saveVenueDetails(@RequestBody VenueDTO venueDTO) throws Exception{
		wizardService.saveVenueDetails(venueDTO);
	}

	@RequestMapping(value="/wizard/venue/lookup", method=RequestMethod.GET)
	public LookUpDTO getVenuelookUp() throws Exception{
		return wizardService.getVenuelookUp();

	}
	
	@RequestMapping(value="/wizard/details", method=RequestMethod.GET)
	public WizardDTO getWizardDetails(@RequestParam String username) throws Exception{
		return wizardService.getWizardDetails(username);
	}
	
	@RequestMapping(value="/wizard/populate", method=RequestMethod.GET)
	public WizardDTO populateWizard(@RequestParam String wizardName, @RequestParam String wizardType) throws Exception{
		return wizardService.populateWizard(wizardName, wizardType);
	}
	
	@RequestMapping(value="/wizard/loadLocalities", method=RequestMethod.GET)
	public Map<Long, String> getLocalities(@RequestParam Long cityId) throws Exception{
		return wizardService.getLocalities(cityId);
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
