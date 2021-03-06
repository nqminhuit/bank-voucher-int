package com.nqminhuit.gateway.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nqminhuit.gateway.filters.services.GatewayAuthentication;
import org.springframework.http.HttpStatus;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authz = req.getHeader("authorization");

        HttpServletResponse res = (HttpServletResponse) response;
        if (authz == null || authz.isBlank()) {
            res.sendError(HttpStatus.FORBIDDEN.value(), "Authentication is required!");
            return;
        }

        String jwt = authz.replaceFirst("Bearer", "").trim();
        String phoneNumber = req.getParameter("phoneNumber");
        if (!GatewayAuthentication.verify(jwt, phoneNumber)) {
            res.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid access token!");
            return;
        }
        chain.doFilter(request, response);
    }

}
