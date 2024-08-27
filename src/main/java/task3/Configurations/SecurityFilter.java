package task3.Configurations;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter extends HttpFilter {

    private String getAdminToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = getAdminToken(request);

        if (token == null) {
            System.out.println("No Authorization token found.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
            return;
        }

        if (!"Admin".equals(token)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
            return;
        }

        chain.doFilter(request, response);
    }
}