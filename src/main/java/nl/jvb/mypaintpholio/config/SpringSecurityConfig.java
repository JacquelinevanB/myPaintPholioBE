package nl.jvb.mypaintpholio.config;

import nl.jvb.mypaintpholio.filters.JwtRequestFilter;
import nl.jvb.mypaintpholio.services.CustomUserDetailsService;
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
                .antMatchers(HttpMethod.GET,"/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/{username}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/users/{username}/image").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/projects/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/projects/user/{username}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/projects/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/projects/add_project").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/projects/{id}/{username}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/projects/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/projects/{id}").hasAnyRole("ADMIN", "USER")

                .antMatchers(HttpMethod.GET,"/updates/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/updates/project/{project_id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/updates/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/updates/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/updates/{id}").hasAnyRole("ADMIN", "USER")

                .antMatchers(HttpMethod.GET,"/quotes/random").permitAll()
                .antMatchers(HttpMethod.GET,"/quotes/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/quotes/add_quote").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/quotes/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/quotes/{id}").hasRole("ADMIN")

                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()

                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}