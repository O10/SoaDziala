package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wojtek on 18/05/15.
 */
@Stateless
public class ElementService {
    @PersistenceContext
    EntityManager em;
}
