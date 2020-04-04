package net.arver.oa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class OaSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(OaSecurityConfiguration.class);



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        final String[][] usersGroupsAndRoles = {
            {"mbergljung", "1234", "ROLE_ACTIVITI_USER", "GROUP_activitiTraining"},
            {"testuser", "1234", "ROLE_ACTIVITI_USER", "GROUP_activitiTraining"},
            {"system", "1234", "ROLE_ACTIVITI_USER"},
            {"admin", "1234", "ROLE_ACTIVITI_ADMIN"},
        };
        for (final String[] user : usersGroupsAndRoles) {
            final List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            logger.info("> Registering new user:" + user[0] + " with the following Authorities[" + authoritiesStrings + "]");
            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
                authoritiesStrings.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList())));
        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

