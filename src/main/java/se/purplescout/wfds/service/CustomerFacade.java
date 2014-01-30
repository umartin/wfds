package se.purplescout.wfds.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.purplescout.wfds.model.Customer;

/**
 *
 * @author Martin Andersson
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

	@PersistenceContext(unitName = "wfdsPU")
	private EntityManager em;

	public CustomerFacade() {
		super(Customer.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
