/*package lepartycious.configs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response1=(HttpServletResponse) response;

	    response1.setHeader("Access-Control-Allow-Origin", "*");
	    response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
	    response1.setHeader("Access-Control-Max-Age", "3600");
	    response1.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization,x-requested-with");
	    chain.doFilter(request, response1);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
*/