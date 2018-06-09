package com.abc.blogger.controller;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blogger.entity.User;
import com.abc.blogger.repository.UserRepository;
import com.abc.blogger.util.LoginCredentials;
import com.abc.blogger.util.Response;

@RestController
public class Authenticate {
	
	private static final Logger logger= LoggerFactory.getLogger(Authenticate.class);
	
	@Autowired
	private UserRepository userrepo;


	
	@PostMapping(path="/signin")
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Response> signin(@RequestBody LoginCredentials login,HttpServletRequest request  ){
	    try{
		logger.info("Signing in with email "+login.getEmail());
		
		User u=userrepo.findByEmail(login.getEmail());
		if(u==null){
			logger.info("Email not found");
			return new  ResponseEntity<Response>(new Response(new Date(),"Email Not Found"),HttpStatus.NOT_FOUND);
		}
			
		else{
			String pass=getMD5EncryptedValue(login.getPassword());
			
			if(pass.equals(u.getPassword())){
				logger.info("Login Succesfull");
				HttpSession session=request.getSession();
				session.setAttribute("user", login.getEmail());
				
				return new ResponseEntity<Response>(new Response(new Date(),"Login Success"),HttpStatus.OK);
			}
			else{
				logger.info("Wrong password");
				return new ResponseEntity<Response>(new Response(new Date(),"Wrong password"),HttpStatus.UNAUTHORIZED);
			}
		}	
	  }
	    catch(Exception ex){
	    	logger.error(ex.toString());
	    	return new ResponseEntity<Response>(new Response(new Date(),ex.toString()),HttpStatus.SERVICE_UNAVAILABLE);
	    	
	    }
	}
	
	@PostMapping(path="/signup")
	@ResponseBody
	public ResponseEntity<Response> signup(@RequestBody User newuser ){
		
		try {
			if(userrepo.findByEmail(newuser.getEmail())!=null){
				logger.info("Email already used");
				return new ResponseEntity<Response>(new Response(new Date(),"Email already used"),HttpStatus.BAD_REQUEST);
			}
			newuser.setPassword(getMD5EncryptedValue(newuser.getPassword()));
			userrepo.save(newuser);
			logger.info("New User Created");
			return new ResponseEntity<Response>(new Response(new Date(),"New User Created"),HttpStatus.CREATED);
		} catch (Exception ex) {
			logger.error(ex.toString());
	    	return new ResponseEntity<Response>(new Response(new Date(),ex.toString()),HttpStatus.SERVICE_UNAVAILABLE);
	    	
		}
	}
	
    public static String getMD5EncryptedValue(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
           logger.info(nsae.getMessage());
        }
        return password;
    }
}
