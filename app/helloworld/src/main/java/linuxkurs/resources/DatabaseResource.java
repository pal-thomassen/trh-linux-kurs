package linuxkurs.resources;

import com.codahale.metrics.annotation.Timed;
import linuxkurs.api.Database;
import linuxkurs.api.Name;
import linuxkurs.dao.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/database")
public class DatabaseResource {

    private final UserDAO dao;

    public DatabaseResource(UserDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Database createTable() {
        dao.createTable();
        return new Database("OK");
    }

    @POST
    @Path("insert")
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Database insertIntoDatabase(Database database) {
        dao.insert(database.getStatus());
        return new Database("OK");
    }

    @GET
    @Path("list")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<Name> getData() {
        return dao.findNames().stream().map(Name::new).collect(Collectors.toList());
    }

}
