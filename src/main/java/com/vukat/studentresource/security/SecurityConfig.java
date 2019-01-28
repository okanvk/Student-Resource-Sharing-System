package com.vukat.studentresource.security;

import com.vukat.studentresource.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.vukat.studentresource.security.SecurityConstants.SIGN_UP_URLS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)

// Main security configurations
// http-security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedhandler;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public JwtAuthenticationFilter JwtauthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // we disable cors and csrf
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedhandler) // where exception throwed when some not authenticated
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// we don't wanna save cookies or session(we use jwt)
        .and().headers().frameOptions().sameOrigin() // To enable h2-db
        .and().authorizeRequests().antMatchers(
                "/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        ).permitAll() // we dont need to authenticate to access this elements
                .antMatchers(SIGN_UP_URLS).permitAll() // api users üzerinde olan api public
                .antMatchers("/api/university/**").permitAll()
                .antMatchers("/api/faculty/**").permitAll()
                .antMatchers("/api/course/takeCourses/**").permitAll()
                .antMatchers("/api/course/takeCourse/**").permitAll()
                .antMatchers("/api/resource/getResource/**").permitAll()
                .anyRequest().authenticated(); // anything we want access we need authenticate

        http.addFilterBefore(JwtauthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
