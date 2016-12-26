package lepartycious.wizard.venue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lepartycious.Enums.UserTypeEnum;
import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/secured/v1/wizard/venue")
public class VenueWizardController {
	
	@Autowired
	private VenueWizardService venueWizardService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void saveVenueDetails(@RequestBody VenueDTO venueDTO) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user != null && (UserTypeEnum.ADMIN.toString().equalsIgnoreCase(user.getUserRole()) || UserTypeEnum.VENDOR.toString().equalsIgnoreCase(user.getUserRole())))
			venueWizardService.saveVenueDetails(venueDTO);
		else
			throw new Exception("User does not have permission to perform this operation");
	}

	@RequestMapping(value="/lookup", method=RequestMethod.GET)
	public LookUpDTO getVenuelookUp() throws Exception{
		return venueWizardService.getVenuelookUp();

	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public VenueDTO loadVenueWizard(@RequestParam String wizardName) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user != null && (UserTypeEnum.ADMIN.toString().equalsIgnoreCase(user.getUserRole()) || UserTypeEnum.VENDOR.toString().equalsIgnoreCase(user.getUserRole())))
			return venueWizardService.loadVenueWizard(wizardName);
		else
			throw new Exception("User does not have permission to perform this operation");
	}
	
	@RequestMapping(value="/updateActivateStatus", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void updateVenueActivationStatus(@RequestParam String action, @RequestParam Long venueId, @RequestParam String actionComments) throws Exception{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user != null && (UserTypeEnum.ADMIN.toString().equalsIgnoreCase(user.getUserRole())))
			venueWizardService.updateVenueActivationStatus(action, venueId, actionComments);
		else
			throw new Exception("User does not have permission to perform this operation");
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
