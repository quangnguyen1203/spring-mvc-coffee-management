package com.codegym.casestudy.security;

import com.codegym.casestudy.serivce.user.IUserService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private IUserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/api/login", "/resource/**", "/api/register", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin")
                .and().exceptionHandling().accessDeniedPage("/error-403")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println("Login successful");
//                        System.out.println("Username: " + authentication.getName());
//                        Cookie jwtTokenCookie = new Cookie("RoleAuth", "ABCDEFGH123456789");
//                        jwtTokenCookie.setMaxAge(86400);
//                        jwtTokenCookie.setSecure(false);
//                        jwtTokenCookie.setHttpOnly(false);
//                        jwtTokenCookie.setPath("/login");
//                        jwtTokenCookie.setDomain("localhost");
//                        response.addCookie(jwtTokenCookie);
//                    }
//                })
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().csrf().disable();

        http.logout()
                .logoutSuccessUrl("/login")
                .logoutUrl("/logout")
                .deleteCookies("JWT").invalidateHttpSession(true)
                .permitAll();

//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .csrf().disable();
    }
}
