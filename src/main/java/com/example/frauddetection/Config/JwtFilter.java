package com.example.frauddetection.Config;

import com.example.frauddetection.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String path = request.getServletPath();
        final String authHeader = request.getHeader("Authorization");

        System.out.println("JwtFilter: path=" + path + ", Authorization=" + authHeader);

        // No Authorization header or not Bearer -> continue (endpoint may be public)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("JwtFilter: no Bearer token, continuing filter chain");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String username;

        try {
            username = jwtService.extractUsername(jwt);
            System.out.println("JwtFilter: extracted username=" + username);
        } catch (Exception e) {
            // Invalid or expired token -> return 401 with JSON
            System.out.println("JwtFilter: failed to extract username from token: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String json = """
            {
                "status": 401,
                "error": "Unauthorized",
                "message": "Invalid or expired token"
            }
            """;
            response.getWriter().write(json);
            return;
        }

        // If we got a username and the user is not yet authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("JwtFilter: loading userDetails for username=" + username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.println("JwtFilter: loaded userDetails=" + userDetails.getUsername()
                    + ", authorities=" + userDetails.getAuthorities());

            if (jwtService.isTokenValid(jwt, userDetails)) {
                System.out.println("JwtFilter: token is valid, setting authentication");
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("JwtFilter: SecurityContext set with authorities="
                        + authToken.getAuthorities());
            } else {
                // Token exists but not valid for that user -> 401
                System.out.println("JwtFilter: token is NOT valid for user " + username);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                String json = """
                {
                    "status": 401,
                    "error": "Unauthorized",
                    "message": "Invalid or expired token"
                }
                """;
                response.getWriter().write(json);
                return;
            }
        } else {
            System.out.println("JwtFilter: username is null or user already authenticated, skipping auth");
        }

        // Continue the filter chain for valid or unauthenticated-but-allowed requests
        filterChain.doFilter(request, response);
    }
}