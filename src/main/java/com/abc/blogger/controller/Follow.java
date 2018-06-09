package com.abc.blogger.controller;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blogger.entity.Relation;
import com.abc.blogger.entity.User;
import com.abc.blogger.repository.RelationRepository;
import com.abc.blogger.repository.UserRepository;
import com.abc.blogger.util.Response;

@RestController
public class Follow {
	private static final Logger logger= LoggerFactory.getLogger(Follow.class);
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private RelationRepository relrepo;

	@PostMapping(path="/follow")
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Response> follow(@RequestBody Relation input){
		
		logger.info("Adding relation "+input.getFollower()+" "+input.getFollowing());
		
		try{
			
			if(userrepo.existsById(input.getFollowing())==false){
				logger.info("Following id does not exits");
				return new  ResponseEntity<Response>(new Response(new Date(),"Following id does not exits"),HttpStatus.NOT_FOUND);
			}
			
			if(userrepo.existsById(input.getFollower())==false){
				logger.info("Follower id does not exits");
				return new  ResponseEntity<Response>(new Response(new Date(),"Follower id does not exits"),HttpStatus.NOT_FOUND);
			}
			
			relrepo.save(input);
			logger.info("Relationship added");
			return new  ResponseEntity<Response>(new Response(new Date(),"Following "+input.getFollowing()),HttpStatus.CREATED);
		}
		catch(Exception ex){
			logger.info(ex.toString());
			return new  ResponseEntity<Response>(new Response(new Date(),ex.toString()),HttpStatus.NOT_FOUND);
		}
	}
}
