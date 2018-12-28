/**
 * 
 */
package com.ngser.service;

import java.util.List;
import java.util.UUID;

/**
 * @author ars
 *
 */
public interface Repository<E> {

	E save(E entity); // Save entity in db --- create / update POST

	E update(E entity);// Save entity in db --- create / update POST

	E findbyId(UUID id); // get an Entity by Id ---- get / get
	E findbyname(String name); // get an Entity by name ---- get / get


	List<E> findall(); // get the list of Entity -- getAll

	Boolean delete(UUID id);// Delete and Entity - remove - Delete

	
}
