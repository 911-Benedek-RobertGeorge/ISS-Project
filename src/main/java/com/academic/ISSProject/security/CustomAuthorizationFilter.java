package com.academic.ISSProject.security;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("dofilter : ");
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/login/refresh-token")){
            log.info("dont filter login");
            filterChain.doFilter(request,response);

        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            log.info("Authorization : ");
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    log.info("Here1");
                    Algorithm algorithm = Algorithm.HMAC256("${jwt.secret}".getBytes());
                    log.info("Here2");
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    log.info("Here3");
                    DecodedJWT decodedJWT = verifier.verify(token);
                    log.info("Here4");
                    String username = decodedJWT.getSubject();
                    log.info("Here5");
                    ///TODO OR IS a simple string
                    String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                    log.info(roles.toString());
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info(" --> Success");
                    filterChain.doFilter(request,response);
                }catch (Exception exception){
                    log.error("Error logging in {}" , exception.getMessage());
                    response.setHeader("Error",exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String,String> error = new HashMap<>();

                    error.put("error_message",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

}
