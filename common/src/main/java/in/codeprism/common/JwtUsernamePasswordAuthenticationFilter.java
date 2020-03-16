package in.codeprism.common;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Authenticate the request to url /login by POST with json body '{ username, password }'.
 * If successful, response the client with header 'Authorization: Bearer jwt-token'.
 *
 * 
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationConfig config;
    private final ObjectMapper mapper;

    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(config.getUrl(), "POST"));
        setAuthenticationManager(authManager);
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
            throws AuthenticationException, IOException {
        User u = mapper.readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                u.getUsername(), u.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) throws IOException {
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
                .compact();
     
       // rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token);
        
        rsp.setContentType("application/json");
        rsp.setCharacterEncoding("UTF-8");
        rsp.getWriter().write(
        	
        		
        	//"{\"" + config.getHeader() + "\":\"" + config.getPrefix() +" "+ token + "\"}"
        	
        		
         // " studnetId : 69, studentname:jai ,lastname:singh" +token
        		
		//""token :""+token  );
        		
        		"{\""+"token"+"\":\""+token+"\""+","+   
        		
        	"\r\n\"userinfo\" : [ { \"studentID\" : \"669\", \"userdata\" :\r\n\t\t  \"{\\\"enrolled\\\":[\\\"201901311055265c52c60ef11aa\\\"]}\", \"firstname\" : \"Godwin\",\r\n\t\t  \"password\" : \"40430383aa399ef2c3af8ef4232d660fb93b057a\", \"mobile\" : \"\",\r\n\t\t  \"usertype\" : \"admin\", \"courses_data\" : null, \"email\" :\r\n\t\t  \"alfa.godwin.omega@gmail.com\", \"lastname\" : \"VC\", \"username\" : \"godwinvc\",\r\n\t\t  \"token\" : \"godwinvc|5e620e6faedb95e620e6faedd55e620e6faedd6\" }] "	
        		
        		
        		
        		
        		+ "}"  );
        		//"{\"" + "token" + " "+ ":" + token + "\"}");
		  /*",
		  "userinfo" : [ { "studentID" : "669", "userdata" :
		  "{\"enrolled\":[\"201901311055265c52c60ef11aa\"]}", "firstname" : "Godwin",
		  "password" : "40430383aa399ef2c3af8ef4232d660fb93b057a", "mobile" : "",
		  "usertype" : "admin", "courses_data" : null, "email" :
		  "alfa.godwin.omega@gmail.com", "lastname" : "VC", "username" : "godwinvc",
		  "token" : "godwinvc|5e620e6faedb95e620e6faedd55e620e6faedd6" } "
		         		
        		
        
		);
		*//*
		 * rsp.setContentType("application/json"); rsp.setCharacterEncoding("UTF-8");
		 * rsp.getWriter().write( "{\"" + SecurityConstants.HEADER_STRING + "\":\"" +
		 * SecurityConstants.TOKEN_PREFIX+token + "\"}" );
		 */
    }

   
    private static class User {
        private String username, password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
    }
}
