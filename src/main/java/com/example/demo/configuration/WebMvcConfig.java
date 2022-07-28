package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebMvcConfig extends WebSecurityConfigurerAdapter {
    public final AuthenticationFilter authenticationFilterBean;

    public WebMvcConfig(AuthenticationFilter authenticationFilterBean) {
        this.authenticationFilterBean = authenticationFilterBean;
    }

    @Override
    protected void configure(HttpSecurity auth) throws Exception {

            final String[] authWhitelist = {"/**","/api/department/**","/webjars/**","/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**","/api/role/**","/api/user/**","/api/auth/**"};
                 auth
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(authWhitelist).permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and().addFilterBefore(authenticationFilterBean, UsernamePasswordAuthenticationFilter.class)
                    .headers().cacheControl();


        }

}
