package lepartycious.controllers.reviews;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lepartycious.Error.Error;
import lepartycious.dtos.requestDTOs.ReviewCommentRequestDTO;
import lepartycious.dtos.requestDTOs.SearchRequestDTO;
import lepartycious.dtos.responseDTOs.ReviewCommentWrapperDTO;
import lepartycious.services.ReviewCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rest")
public class ReviewCommentController {
	
	@Autowired
	private ReviewCommentService reviewCommentService;
	
	@RequestMapping(method=RequestMethod.POST, value="/secured/v1/review/submit")
	@ResponseStatus(HttpStatus.OK)
	public void submitReviewForAppUser(@RequestBody ReviewCommentRequestDTO reviewCommentRequestDTO) throws Exception {
		reviewCommentService.submitReview(reviewCommentRequestDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/v1/review/getAllReviews")
	public ReviewCommentWrapperDTO getReviewsByVendor(@RequestBody SearchRequestDTO searchRequestDTO) throws Exception {
		if(searchRequestDTO.getLimit() == null){
			searchRequestDTO.setLimit(50l);
		}
		Long offset = (searchRequestDTO.getOffset()-1)*searchRequestDTO.getLimit();
		searchRequestDTO.setOffset(offset);
		return reviewCommentService.getReviewsByVendor(searchRequestDTO);
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
