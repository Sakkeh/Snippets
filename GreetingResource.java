package sakke.koodaa.quarkus;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hellop(@QueryParam("metadata") String metadataArray) {
        Jsonb jsonb = JsonbBuilder.create();
        List<MetadataObj> myObjects = jsonb.fromJson(metadataArray, new ArrayList<MetadataObj>(){}.getClass().getGenericSuperclass());
        boolean containsCompany = myObjects.stream()
            .anyMatch(p -> p.getKey().equals("company"));
        System.out.println(myObjects);
        return "Hello from RESTEasy Reactive. Contains company: " + containsCompany;
    }    

    // Write a method to validate JSON string

}
