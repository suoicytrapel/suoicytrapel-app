package lepartycious.wizard;

import java.util.Map;

import lepartycious.dtos.requestDTOs.VenueDTO;
import lepartycious.dtos.responseDTOs.LookUpDTO;
import lepartycious.dtos.responseDTOs.WizardDTO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface WizardService {

	Map<Long, String> getLocalities(Long cityId) throws Exception;

}
