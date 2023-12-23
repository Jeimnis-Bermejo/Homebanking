
package com.mindhub.homebanking.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Enabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
http.authorizeHttpRequests(auth ->
        auth.requestMatchers("/web/image/*", "/web/pages/index.html", "/web/js/index.js","/web/js/tailwind.config.js").permitAll()

                .requestMatchers("/web/**","/api/clients/current").hasAnyAuthority("CLIENT","ADMIN")
                .requestMatchers("/h2-console/**").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .anyRequest().denyAll());

http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
http.headers(header->header.frameOptions(option->option.disable()));
http.formLogin(formLogin ->
        formLogin.loginPage("/index.html")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/api/login")
                .failureHandler((request, response, exception) -> response.sendError(403))
                        .successHandler((request, response, authentication) ->clearAuthenticationAttributes(request))
                .permitAll());
http.logout(logout->
        logout.logoutUrl("/api/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()));

http.exceptionHandling(exceptionHandlingConfigurer ->
        exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) ->
                response.sendError(401)));


        http.rememberMe(Customizer.withDefaults());


return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }


}

