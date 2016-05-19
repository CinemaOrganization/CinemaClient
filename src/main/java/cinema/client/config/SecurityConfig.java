package cinema.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("cinema.client")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, true " +
                                "from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from users, role, users_roles " +
                                "where users.id_user = users_roles.id_user " +
                                "and role.id_authority = users_roles.id_authority" +
                                " and users.username=?");
        //.passwordEncoder(new StandardPasswordEncoder("27еЬ3t"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/login*").anonymous()
                .antMatchers("/user/registration*").anonymous()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .failureUrl("/user/login?error=true")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/user/login")
                //Impotent thing
                .and().csrf().disable();
           //     .rememberMe();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }
}
