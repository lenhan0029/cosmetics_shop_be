 package com.cosmetics.cosmetics.Config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cosmetics.cosmetics.Security.JwtAuthEntryPoint;
import com.cosmetics.cosmetics.Security.JwtAuthTokenFilter;
import com.cosmetics.cosmetics.Security.JwtUtils;
import com.cosmetics.cosmetics.Security.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    final private JwtAuthEntryPoint unauthorizedHandler;

    private final JwtUtils jwtUtils;

    public WebSecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthEntryPoint unauthorizedHandler, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }
    
    

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // TODO
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println(userDetailsService.toString() + "+++++++++++++++++++++++++");
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/auth/**","/account/**", "/api/public/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .antMatchers(HttpMethod.GET,"/brand/**","/type/**","/category/**","/product/**","/statistical/**","/rate/**").permitAll()
            .antMatchers(HttpMethod.GET,"/cartdetail/**").hasAuthority("member")
            .antMatchers(HttpMethod.POST,"/cartdetail/**","/order/**").hasAuthority("member")
            .antMatchers(HttpMethod.PUT,"/userInformation/*","/cartdetail/**").hasAuthority("member")
            .antMatchers(HttpMethod.DELETE,"/cartdetail/**").hasAuthority("member")
            .antMatchers(HttpMethod.GET,"/statistical/**").hasAnyAuthority("admin")
            .antMatchers(HttpMethod.POST,"/brand/**","/type/**","/category/**","/product/**","/vouchers/**").hasAuthority("admin")
            .antMatchers(HttpMethod.PUT,"/brand/**","/type/**","/category/**","/vouchers/**","/account/admin/**","/order/**").hasAnyAuthority("admin","member","shipper")
            .antMatchers(HttpMethod.DELETE,"/brand/**","/type/**","/category/**","/vouchers/**").hasAuthority("admin")
            .antMatchers(HttpMethod.GET,"/order/**","/userInformation/**","/vouchers/**").hasAnyAuthority("admin","member","shipper")
        	.anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
