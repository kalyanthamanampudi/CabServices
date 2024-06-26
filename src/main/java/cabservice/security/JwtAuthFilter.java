package cabservice.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cabservice.controller.CustomerController;
import cabservice.controller.DriverController;
import cabservice.controller.ManagementController;
import cabservice.serviceimpl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtServiceImpl js;

	@Autowired
	private UserInfo userdetailservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		init(request.getRequestURI().toString());

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = js.extractusername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userdetailservice.loadUserByUsername(username);

			if (js.validateToken(token, user)) {
				UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);

			}
		}
		filterChain.doFilter(request, response);
	}

	private void init(String uri) {
		if (uri.contains("customer"))
			CustomerController.act();
		else if (uri.contains("driver"))
			DriverController.act();
		else if (uri.contains("management"))
			ManagementController.act();
	}

}
