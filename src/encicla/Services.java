package encicla;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/rest_services")
public class Services {
	
	//http://localhost:8080/encicla/rest/rest_services
	@GET
	@Path("{function_name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(@PathParam("function_name") String functionName) {
		switch (functionName) {
		case "text":
			return "Hello Text";
		case "xml":
			return "Hello xml";
		case "json":
			return "Hello json";
		default:
			return "Resource not found";
		}
	}
	
	//http://localhost:8080/encicla/rest/rest_services/getUsers?full_data=false
	@GET
	@Path("getUsers")
	@Produces({"application/xml", "application/json"})
	public String getUsers(@QueryParam("full_data") Boolean fullData) {
		if(fullData){
			return "{name:'julio', lastname:'diaz', phone:'12323', address:'clle 45b n 78-23'}";
		}else{
			return "{name:'julio', lastname:'diaz'}";
		}
	}
	
	@GET
	@Path("/get_json")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getJSON() {

		Track result = new Track();
		result.setSinger("juanes");
		result.setTitle("Solor");
		return result;
	}
	  
	@POST
    @Path("/post")
    public String postStrMsg( String msg) {
        return "POST:Jersey say : " + msg;
    }
	
	@POST
    @Path("/user")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({"application/xml", "application/json"})
    public String userXform(@FormParam("user_id") Integer userId) {
        return "Using form x-www-form-urlencoded " + userId;
    }
	
	@POST
    @Path("/user")
	@Produces({"application/xml", "application/json"})
    public String userParams(@QueryParam("user_id") Integer userId) {
        return "Using params " + userId;
    }
	
	@POST
	@Path("/post_json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postJSON(Track track) {
		
		track.setSinger("shakira");
		track.setTitle("No");
		return Response.status(200).entity(track).build();
	}
	
	@POST
	@Path("/post_array")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Track> postArray(Track track) {
		List<Track> tracks = new ArrayList<Track>();
		tracks.add(track);
		Track track2 = new Track();
		track2.setSinger("shakira");
		track2.setTitle("No");
		tracks.add(track2);
		return tracks;
	}
	
	@POST
    @Path("/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public String uploadFileWithData(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition cdh,
            @FormDataParam("description") String description) throws Exception{

        return "Using form-data " + description + " de " + cdh.getFileName();
    } 
}
