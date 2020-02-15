package edu.sandau.service;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sandau.entity.UserInfo;

@Path("user")
public interface UserService {
    @POST
    @Path("/update") //访问localhost(换成服务器ip):8888/rest/auth/login
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UserInfo updateLogin(Map<Object,String> userMap);
}
