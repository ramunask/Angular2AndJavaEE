package ch.xxx.carrental.ui.rest.model;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrTableService;

@Path("/model/crTable/mietNr/{mietNr}")
@Produces({ "application/json" })
public class CrTableResource {
	@Inject
	private CrTableService service;

	@GET
	@DisableCaching
	public Response getAll(@PathParam("mietNr") final String mietNr, @HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return Response.ok(service.readCrRowsByMiete(mietNr)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers",
							"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").allow("OPTIONS").build();
		} else {
			return Response.ok(service.readCrRowsByMiete(mietNr)).build();
		}

	}

	@OPTIONS
	@DisableCaching
	public Response getOptions(@HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
					.header("Access-Control-Allow-Headers",
							"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
					.allow("OPTIONS").build();
		} else {
			return Response.ok().build();
		}
	}
}
