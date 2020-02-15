package edu.sandau.service;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.sandau.entity.UserInfo;

@Path("auth")

public interface AuthService {

	@GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    Map<String, UserInfo> queryAll();

	@POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UserInfo addUser(UserInfo user, @QueryParam("requestid") int requestid);
	
	@POST
	@Path("/login") //访问localhost(换成服务器ip):8888/rest/auth/login
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UserInfo  login( Map<String,String> userMap);


	

}
