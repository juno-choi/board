package com.juno.simple.config.security.filter;

import com.juno.simple.config.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final String AUTH_PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null || !token.startsWith(AUTH_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.replace(AUTH_PREFIX, "");
        Authentication authentication = tokenProvider.getAuthentication(token);
        // security 인증 정보 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("memberId", authentication.getName());
        filterChain.doFilter(request, response);
    }
}
