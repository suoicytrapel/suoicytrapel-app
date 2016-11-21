package lepartycious.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	private static final String APP_DATE_FORMAT = "dd-mm-yyyy";
	
	public static String getFormattedDate(Date actualDate){
		SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
		String formattedDate = formatter.format(actualDate);
		return formattedDate;
	}
	
}
