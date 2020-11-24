
package org.uv;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("empleados")
public class EmpleadosResource {

    @Context
    private UriInfo context;
    private EmpleadoDAO dao = EmpleadoDAO.getInstance();

    /**
     * Creates a new instance of EmpleadosResource
     */
    public EmpleadosResource() {
    }

    /**
     * Retrieves representation of an instance of org.uv.EmpleadosResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Empleado getJson() {
//        Empleado emp = new Empleado();
//        emp.setId("12");
//        emp.setNombre("gustavo");
//        emp.setDireccion("av 1");
//        emp.setTelefono("321456897");
//        //TODO return proper representation object
//        return emp;
//    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empleado> list() {
	return dao.listAll();
    }
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Empleado empleado) throws URISyntaxException {
		String newProductId = dao.add(empleado);
		URI uri = new URI("/products/" + newProductId);
		return Response.created(uri).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") String id, Empleado empleado) {
		empleado.setId(id);
		if (dao.update(empleado)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String id) {
		if (dao.delete(id)) {
			return Response.ok().build();					
		} else {
			return Response.notModified().build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) {
		Empleado empleado = dao.get(id);
		if (empleado != null) {
			return Response.ok(empleado, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
    
    

    /**
     * PUT method for updating or creating an instance of EmpleadosResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
}
