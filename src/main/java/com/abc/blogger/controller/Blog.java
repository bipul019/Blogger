package com.abc.blogger.controller;

import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blogger.entity.Post;
import com.abc.blogger.entity.Relation;
import com.abc.blogger.repository.PostRepository;
import com.abc.blogger.repository.RelationRepository;
import com.abc.blogger.repository.UserRepository;
import com.abc.blogger.util.BuzzWord;
import com.abc.blogger.util.Comment;
import com.abc.blogger.util.Response;

@RestController
public class Blog {
	private static final Logger logger= LoggerFactory.getLogger(Blog.class);
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private PostRepository postrepo;
	
	@Autowired
	private BuzzWord buzzword;
	

	@PostMapping(path="/post")
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Response> follow(@RequestBody Comment input){
		
		
		logger.info("Blog Posted by"+input.getUser_id());
		
		try{
			
			if(userrepo.existsById(input.getUser_id())==false){
				logger.info("User id does not exits");
				return new  ResponseEntity<Response>(new Response(new Date(),"User id does not exits"),HttpStatus.NOT_FOUND);
			}
			
			logger.info("Tokenising "+ input.getTweet());
			
			
			int count=0;
			Set<String> table=buzzword.getTable();
			
			StringTokenizer st =new StringTokenizer(input.getTweet(), " ");
		        while (st.hasMoreTokens()){
		            if(table.contains(st.nextToken().toLowerCase()))
		            	count++;
		        }
		        
		        logger.info("Buzz count after tokenizing " +count);
		        
		        Post p=new Post();
		        p.setUser_id(input.getUser_id());
		        p.setDate(new Date());
		        p.setBuzzcount(count);
		        p.setComment(input.getTweet());
		        
		        postrepo.save(p);
		    	logger.info("Post Added");
				return new  ResponseEntity<Response>(new Response(new Date(),"Blog Posted"),HttpStatus.OK);
		}
		
		catch(Exception ex){
			logger.info(ex.toString());
			return new  ResponseEntity<Response>(new Response(new Date(),ex.toString()),HttpStatus.NOT_FOUND);
		}
	}
}

