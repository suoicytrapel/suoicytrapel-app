package lepartycious.configs;

import lepartycious.services.SecurityUserDetailsService;
import lepartycious.services.implementations.SecurityUserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableAuthorizationServer
public class TokenConfig extends AuthorizationServerConfigurerAdapter{
	
	private TokenStore tokenStore = new InMemoryTokenStore();
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityUserDetailsService userDetailService;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailService);
	}
	
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory().withClient("browser").authorizedGrantTypes("refresh_token", "password").scopes("ui").scopes("external");
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
		
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}
