package com.cookingrecipe.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.accept.ContentNegotiationStrategy;

import com.cookingrecipe.entity.Role;
import com.cookingrecipe.service.user.IUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("accessDeniedHandler")
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	@Qualifier("authenticationSuccessHandler")
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@Override
	public void setContentNegotationStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
		// TODO Auto-generated method stub
		System.out.println("In content negotiation...");
		super.setContentNegotationStrategy(contentNegotiationStrategy);
	}

//	@Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authentication)
//            throws Exception
//    {
//        authentication.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .authorities("ROLE_USER");
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**").permitAll().antMatchers("/webjars/**").permitAll()
		.antMatchers("/").permitAll()
        .antMatchers("/home").access("hasRole('USER')")
        .antMatchers("/resources/**").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .formLogin().loginPage("/login").permitAll()
        .usernameParameter("email").passwordParameter("password")
        .defaultSuccessUrl("/loginSuccess").permitAll()
        .failureUrl("/loginSuccess")
        .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
		;
		http.csrf();//.disable()
		http.headers();
		http.httpBasic();
		/*http.authorizeRequests().antMatchers("/resources/**").permitAll().antMatchers("/webjars/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signup").permitAll()
//	        .antMatchers("/users/add").permitAll()
				.antMatchers(HttpMethod.POST, "/users").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/access_denied").permitAll()
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
				.antMatchers("/").permitAll()
				.anyRequest().hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

				.and().csrf().disable()
				.formLogin().loginPage("/login").failureUrl("/login?error=true")

				.usernameParameter("email").passwordParameter("password").successHandler(authenticationSuccessHandler)
				.loginProcessingUrl("/login").defaultSuccessUrl("/loginSuccess").permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).and().csrf().and()
				.headers().contentSecurityPolicy(
					"script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/");*/	
//			.httpBasic();
	}

//	@Bean
//	public AccessDeniedHandler accessDeniedHandler(){
//	    return new CustomAccessDeniedHandler();
//	}

//	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/css/**", "/js/**", "/img/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("In authentication config...");
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//	@Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder);
//        return auth;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

}