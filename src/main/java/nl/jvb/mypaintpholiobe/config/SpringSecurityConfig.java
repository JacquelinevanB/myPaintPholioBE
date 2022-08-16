package nl.jvb.mypaintpholiobe.config;

import nl.jvb.mypaintpholiobe.filters.JwtRequestFilter;
import nl.jvb.mypaintpholiobe.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET,"/users/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/users/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/projects/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/projects/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/projects/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/projects/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/projects/**").hasAnyRole("ADMIN", "USER")

                .antMatchers(HttpMethod.GET,"/updates/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/updates/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/updates/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/updates/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/updates/**").hasAnyRole("ADMIN", "USER")

                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()

                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}