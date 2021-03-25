package com.myweb.bookswap.secu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myweb.bookswap.service.BookSwapOauth2UserService;



import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    BookSwapOauth2UserService Oauth2userservice;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from " +
//                        "bswapuser  where username=?")
//                .authoritiesByUsernameQuery("select username,role from authorities where username=?");
    	
    //	auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    	
    	//auth.userDetailsService(userDetailsService)auth.set
    	
    	auth.authenticationProvider(authProvider());
    	//think about jwt based authonication later whether to use
    }

    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    
    @Bean
    public PasswordEncoder encoder() {
    	
        return new BCryptPasswordEncoder();
     
    }

   
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/add").hasRole("USER")
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .formLogin().loginPage("/signinpage")
                .loginProcessingUrl("/signin")
                .failureUrl("/signin-error")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
//                .logout().logoutSuccessUrl("/");
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/signout")).logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(Oauth2userservice)
                .and()
                .successHandler(new Oauth2SuccessHandler());


        /*

        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()

                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();*/

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
