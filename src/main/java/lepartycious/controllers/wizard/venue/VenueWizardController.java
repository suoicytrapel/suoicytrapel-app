package lepartycious.controllers.wizard.venue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
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
@RequestMapping("/api/rest/v1/wizard/venue")
public class VenueWizardController {
	
	@Autowired
	private WizardService wizardService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saveVenueDetails(@RequestBody VenueDTO venueDTO) throws Exception{
		wizardService.saveVenueDetails(venueDTO);
	}

	@RequestMapping(value="/lookup", method=RequestMethod.GET)
	public LookUpDTO getVenuelookUp() throws Exception{
		return wizardService.getVenuelookUp();

	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public VenueDTO loadVenueWizard(@RequestParam String wizardName) throws Exception{
		return wizardService.loadVenueWizard(wizardName);
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
