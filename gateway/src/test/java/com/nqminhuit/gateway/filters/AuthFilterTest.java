package com.nqminhuit.gateway.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nqminhuit.gateway.filters.services.GatewayAuthentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;

public class AuthFilterTest {

    private AuthFilter authFilter;

    @BeforeEach
    public void setup() {
        authFilter = new AuthFilter();
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        HttpServletRequest request = spy(HttpServletRequest.class);
        doReturn("Bearer somejwtstring").when(request).getHeader(eq("authorization"));
        doReturn("0909123456").when(request).getParameter("phoneNumber");

        HttpServletResponse response = spy(HttpServletResponse.class);
        FilterChain chain = spy(FilterChain.class);

        try (var gatewayAuthenticationMock = mockStatic(GatewayAuthentication.class)) {
            gatewayAuthenticationMock
                .when(() -> GatewayAuthentication.verify(anyString(), anyString()))
                .thenReturn(true);
            authFilter.doFilter(request, response, chain);
        }
        verify(chain, times(1)).doFilter(any(), any());
    }


    @Test
    public void doFilter_errorUnauthorized() throws IOException, ServletException {
        HttpServletRequest request = spy(HttpServletRequest.class);
        doReturn("random-invalid-authorization-header")
            .when(request).getHeader(eq("authorization"));

        HttpServletResponse response = spy(HttpServletResponse.class);
        FilterChain chain = spy(FilterChain.class);

        authFilter.doFilter(request, response, chain);

        verify(chain, times(0)).doFilter(any(), any());

        var statusCodeCaptor = ArgumentCaptor.forClass(Integer.class);
        var msgCodeCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).sendError(statusCodeCaptor.capture(), msgCodeCaptor.capture());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), statusCodeCaptor.getValue().intValue());
        assertEquals("Invalid access token!", msgCodeCaptor.getValue());
    }

    @Test
    public void doFilter_errorForbiddenOnNullAuthorization() throws IOException, ServletException {
        HttpServletRequest request = spy(HttpServletRequest.class);
        doReturn(null).when(request).getHeader(eq("authorization"));

        HttpServletResponse response = spy(HttpServletResponse.class);
        FilterChain chain = spy(FilterChain.class);

        authFilter.doFilter(request, response, chain);

        verify(chain, times(0)).doFilter(any(), any());

        var statusCodeCaptor = ArgumentCaptor.forClass(Integer.class);
        var msgCodeCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).sendError(statusCodeCaptor.capture(), msgCodeCaptor.capture());
        assertEquals(HttpStatus.FORBIDDEN.value(), statusCodeCaptor.getValue().intValue());
        assertEquals("Authentication is required!", msgCodeCaptor.getValue());
    }

    @Test
    public void doFilter_errorForbiddenOnEmptyAuthorization() throws IOException, ServletException {
        HttpServletRequest request = spy(HttpServletRequest.class);
        doReturn("").when(request).getHeader(eq("authorization"));

        HttpServletResponse response = spy(HttpServletResponse.class);
        FilterChain chain = spy(FilterChain.class);

        authFilter.doFilter(request, response, chain);

        verify(chain, times(0)).doFilter(any(), any());

        var statusCodeCaptor = ArgumentCaptor.forClass(Integer.class);
        var msgCodeCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).sendError(statusCodeCaptor.capture(), msgCodeCaptor.capture());
        assertEquals(HttpStatus.FORBIDDEN.value(), statusCodeCaptor.getValue().intValue());
        assertEquals("Authentication is required!", msgCodeCaptor.getValue());
    }

}
