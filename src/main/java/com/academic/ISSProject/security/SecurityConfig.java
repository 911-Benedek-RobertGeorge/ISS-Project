package com.academic.ISSProject.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    ///TODO AUTHORIZATION ONLY FOR a student so a student cant see the other information
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthentificationFilter customAuthentificationFilter = new CustomAuthentificationFilter(authenticationManagerBean());
        //customAuthentificationFilter.setFilterProcessesUrl("");
        http.csrf().disable();
        log.info("antmatchers setUp : AUTHORIZATION \n");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();

       /* http.authorizeRequests().antMatchers(HttpMethod.GET,"/students/**").hasAnyAuthority("STUDENT","TEACHER","STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/students/**").hasAnyAuthority( "TEACHER","STAFF");
         http.authorizeRequests().antMatchers(HttpMethod.PUT,"/students/**").hasAnyAuthority( "STUDENT");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/students/**").hasAnyAuthority( "STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/teachers/**").hasAnyAuthority("TEACHER","STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/teachers/**").hasAnyAuthority("TEACHER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/teachers/**").hasAnyAuthority("STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/teachers/**").hasAnyAuthority("STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/staff/**").hasAnyAuthority( "STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/staff/**").hasAnyAuthority( "STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/staff/**").hasAnyAuthority( "STAFF");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/staff/**").hasAnyAuthority( "STAFF");
*/
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();

        http.addFilter(customAuthentificationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
