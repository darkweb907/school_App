package com.darkweb.school.managent.configuration;

import com.darkweb.school.managent.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig {
    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security){
        security.csrf(csrf->csrf.disable()); // to disable csrf
        security.authorizeHttpRequests(request-> request
                .requestMatchers("/api/v1/register" , "/api/v1/login").permitAll().anyRequest().authenticated());
        //security.formLogin(login-> login.permitAll());
        security.httpBasic(Customizer.withDefaults());
        //this is if you dont want to use csrf oo i.e csrf was disable
        security.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);
        security.cors(Customizer.withDefaults());

        //how it works under the hood
//        Customizer<CsrfConfigurer<HttpSecurity>> secured = new Customizer<CsrfConfigurer<HttpSecurity>>(){
//
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> secure) {
//                secure.disable();
//            }
//        }
//        security.csrf(secured);

        return security.build();
    }

//    @Bean
//    another way of securing your app but not still safe cause we are not working from DB
//   public UserDetailsService userDetails(){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("babs")
//                .password("babatunde")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }



//    working with database
@Bean
public AuthenticationProvider provider() {
    DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider(userDetailsService);
    authenticate.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return authenticate;
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config){

        return config.getAuthenticationManager();

}
}
