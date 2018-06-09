package com.abc.blogger.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blogger.entity.Post;
import com.abc.blogger.entity.Relation;
import com.abc.blogger.entity.User;
import com.abc.blogger.repository.PostRepository;
import com.abc.blogger.repository.RelationRepository;
import com.abc.blogger.repository.UserRepository;
import com.abc.blogger.util.Response;

@RestController
public class Feed {
	private static final Logger logger= LoggerFactory.getLogger(Feed.class);
	
	@Autowired
	private PostRepository postrepo;
	@Autowired
	private RelationRepository relrepo;
	@Autowired 
	protected JdbcTemplate jdbctemplate;

	@GetMapping(path="/feed")
	@ResponseBody
	@CrossOrigin
	public List<Post> follow(@RequestParam("id") Integer id ){
		
		logger.info("Getting feed for " + id);
		
		try{
			
			
			List<Relation> following=relrepo.findAllByFollower(id);
			String input ="";
			for(int i=0;i<following.size();i++){
				if(i!=following.size()-1)
				input=input+following.get(i).getFollowing()+",";
				else
					input=input+following.get(i).getFollowing();
			}
			logger.info("following "+input);
			
			String sql="SELECT * FROM abc.post where time> date_sub(now(),interval 24 hour) and user_id in ("+input +") order by buzzcount desc";
			logger.info("executing "+sql);
			return  jdbctemplate.query(sql,postdetailmapper);
		}
		catch(Exception ex){
			logger.info(ex.toString());
			return null;
			
		}
		
	}
	
	private static final RowMapper<Post> postdetailmapper=new RowMapper<Post>(){
		
		public Post mapRow (ResultSet rs, int rownum) throws SQLException{
			Post p=new Post(rs.getInt("post_id"),rs.getInt("user_id"),rs.getDate("time"),rs.getInt("buzzcount"),rs.getString("comment"));
			return p;
		}
	};
}
