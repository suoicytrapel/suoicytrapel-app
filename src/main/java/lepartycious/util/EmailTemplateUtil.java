package lepartycious.util;

import lepartycious.models.User;

public class EmailTemplateUtil {
	
	public static String generateMailContentForUser(User user){
		StringBuffer sbf = new StringBuffer();
		sbf.append("Hi " + user.getName() +",<br>We have recieved a new registeration request from your end.<br>");
		sbf.append("Please sit back and relax while Lepartycious team review this request. We will notify you once the request is approved.<br>");
		sbf.append("<br><br>Cheers,<br>Lepartycious Team");
		return sbf.toString();
	}
	
	public static String generateMailContentForAdmin(String vendorName, String vendorType){
		StringBuffer sbf = new StringBuffer();
		sbf.append("Hi Admin,<br>A new vendor request for approval has reached to your dashboard.<br>");
		sbf.append("Vendor Name:" + vendorName + "<br>");
		sbf.append("Vendor Type:" + vendorType +  "<br>");
		sbf.append("Please login to the application and visit dashboard for more details.<br>");
		sbf.append("<br><br>Cheers,<br>Lepartycious Team");
		return sbf.toString();
	}
	
	public static String generateMailContentForAction(String actionComments, User user, String action) {
		StringBuffer sbf = new StringBuffer();
		if("APPROVED".equalsIgnoreCase(action)){
			sbf.append("Hi " + user.getName() +",<br>\nYour Vendor has been added Successfully.<br>");
		}
		else{
			sbf.append("Hi " + user.getName() +",<br>\nYour Vendor registeration request has been rejected because of below reason :-<br>");
			sbf.append("Rejection Reason:" + actionComments);
			sbf.append("Please visit the application, rectify the details and re-submit the registeration");
			sbf.append("<br><br>Cheers,<br>Lepartycious Team");
		}
		return sbf.toString();
	}
}
