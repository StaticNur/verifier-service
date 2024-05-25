package ru.startup.verifier_service.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.startup.verifier_service.security.exception.AuthorizeException;
import ru.startup.verifier_service.security.exception.ExceptionResponse;
import ru.startup.verifier_service.security.exception.InvalidTokenException;
import ru.startup.verifier_service.security.model.Role;
import ru.startup.verifier_service.security.service.JwtTokenService;

import java.io.IOException;
import java.util.Collections;

/**
 * Filter class to intercept and process JWT tokens in incoming requests.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final ObjectMapper jacksonMapper;

    /**
     * Filters incoming requests to process JWT tokens.
     *
     * @param request     the incoming HTTP servlet request
     * @param response    the HTTP servlet response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs during the servlet processing
     * @throws IOException      if an I/O error occurs
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7).trim();

            if (!jwt.isEmpty()) {
                try {
                    email = jwtTokenService.extractEmail(jwt);
                } catch (ExpiredJwtException exception) {
                    sendResponse(response, "The token's lifetime has expired.");
                    return;
                } catch (InvalidTokenException | AuthorizeException e) {
                    sendResponse(response, e.getMessage());
                    return;

                }
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Role role = jwtTokenService.extractRoles(jwt);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, Collections.singleton(new SimpleGrantedAuthority(role.name())));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } catch (RuntimeException e) {
                sendResponse(response, e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Sends an unauthorized response with the specified message.
     *
     * @param response the HTTP servlet response
     * @param message  the error message to be sent in the response
     * @throws IOException if an I/O error occurs
     */
    private void sendResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        String jsonResponse = jacksonMapper.writeValueAsString(exceptionResponse);
        response.getWriter().write(jsonResponse);
    }

}

