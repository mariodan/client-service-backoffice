package ro.enel.backoffice.config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.client.RestTemplate;
import ro.enel.backoffice.config.security.RestAuthenticationEntryPoint;
import ro.enel.backoffice.filter.RequestBodyReaderAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URI = "/v1/login";
    private static final String LOGOUT_URI = "/v1/logout";

    @Bean
    public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
        RequestBodyReaderAuthenticationFilter authenticationFilter = new RequestBodyReaderAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URI, "POST"));
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin}").roles("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .and()
                .withUser("manager")
                .password("{noop}manager")
                .roles("MANAGER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/actuator/**", "/management/**")
                .permitAll()
            .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage(LOGIN_URI)
                .failureHandler(this::loginFailureHandler)
                .and()
            .addFilterBefore(
                authenticationFilter(),
                UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .and()
                .logout()
                .logoutUrl(LOGOUT_URI)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(this::logoutSuccessHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private void loginSuccessHandler(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        response.setStatus(HttpStatus.OK.value());
    }

    private void loginFailureHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException e) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private void logoutSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException {

        response.setStatus(HttpStatus.OK.value());
    }
}
