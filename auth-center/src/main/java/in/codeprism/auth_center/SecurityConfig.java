package in.codeprism.auth_center;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.codeprism.common.JwtAuthenticationConfig;
import in.codeprism.common.JwtUsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {




	@Autowired JwtAuthenticationConfig config;

	@Bean public JwtAuthenticationConfig jwtConfig() { 
		return new
				JwtAuthenticationConfig(); }

	@Autowired private UserDetailsService userDetailsService;

	/*
	 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}

/*	@Autowired 
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				bCryptPasswordEncoder()); }
				*/

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { PasswordEncoder encoder =
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 * auth.inMemoryAuthentication()
	 * .withUser("admin").password(encoder.encode("admin")).roles("ADMIN",
	 * "USER").and()
	 * .withUser("shuaicj").password(encoder.encode("shuaicj")).roles("USER"); }
	 */



	@Override
	protected void configure(HttpSecurity httpSecurity) throws
	Exception {
		httpSecurity.csrf().disable().logout().disable().formLogin().disable().
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().anonymous().and
		().exceptionHandling() .authenticationEntryPoint((req, rsp, e) ->
		rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and() .addFilterAfter(new
				JwtUsernamePasswordAuthenticationFilter(config, authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated(
				); }




}
