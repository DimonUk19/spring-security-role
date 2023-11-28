package com.student.security;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {

		//System.out.println("  onAuthenticationSuccess redirection         "  );
		
		String adminHome = "/adminHome";
		String userHome = "personalAccount";

		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		System.out.println(authentication.getAuthorities());
				//+ authorities.stream().anyMatch(a -> "user".equals(a.getAuthority())));
		if (authorities.contains("user")) {
			response.sendRedirect(userHome);
			//getRedirectStrategy().sendRedirect(request, response, userHome);
		}
		else if(authorities.contains("admin")) {
			getRedirectStrategy().sendRedirect(request, response, adminHome);
			
		}
		
	}
}
