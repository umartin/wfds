package se.purplescout.wfds.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.purplescout.wfds.model.Customer;

/**
 *
 * @author Martin Andersson
 */
@Stateless
@Path("customer")
public class CustomerFacadeREST {

	@Inject CustomerFacade customerFacade;

	@GET
	@Path("create/{name}")
    @Produces({"application/json"})
	public Customer create(@PathParam("name") String customerName) {
		Customer customer = new Customer();
		customer.setName(customerName);
		customerFacade.create(customer);
		return customerFacade.find(customer.getId());
	}

	@GET
    @Path("list/{id}")
    @Produces({"application/json"})
	public Customer find(@PathParam("id") Integer id) {
		return customerFacade.find(id);
	}

	@GET
	@Path("list")
    @Produces({"application/json"})
	public List<Customer> findAll() {
		return customerFacade.findAll();
	}

	@GET
	@Path("testds")
    @Produces({"text/plain"})
	public String testDS() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/MyDS");
		} catch (NamingException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "datasource not found", ex);
			return "datasource not found";
		}
		return "datasource found";
	}
}
