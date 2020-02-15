package edu.sandau.service;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sandau.entity.Page;
import edu.sandau.entity.UserInfo;

@Path("worryTopic")
public interface WorryTopicService {

    @GET
    @Path("/show") //访问localhost(换成服务器ip):8888/rest/auth/login
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Page showWorryTopic();
}
