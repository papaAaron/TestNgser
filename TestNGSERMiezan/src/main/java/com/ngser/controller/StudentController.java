/**
 * 
 */
package com.ngser.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngser.entities.Student;
import com.ngser.service.StudentService;


/**
 * @author ars
 * @create Nov 2, 2018
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test/student")
public class StudentController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentService studentService;
	
	

	/**
	 * this function is used to save a student entity
	 * 
	 * @param entity
	 * @return entity saved
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Student> add(@RequestBody Student entity) {
		try {

			log.debug("adding student," + entity.toString());

			// checking if requirement parameters are set
			if (entity.getStudentFirstName() == null ) {

				// ===> not set return several parametters are not informed
				log.error("several parametters are not informed," + entity.toString());

				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} else if (studentService.findbyname(entity.getStudentFirstName().trim()) == null) {

				// all data are OK for saving
				// setting other optional data
				
				entity = studentService.save(entity);

				log.debug("success added student," + entity.toString());
				return new ResponseEntity<>(entity, HttpStatus.CREATED);
			} else {

				log.error("object with same name already exist," + entity.toString());
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

		} catch (Exception e) {
			log.debug("error while adding student," + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Student> update(@RequestBody Student entity) {
		try {
			log.debug("updating student," + entity.toString());

			// checking if requirement parameters are set
			if (entity.getStudentFirstName().trim().isEmpty() || entity.getStudentFirstName() == null) {

				// ===> not set return several parametters are not informed
				log.error("several parametters are not informed," + entity.toString());

				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

			} else if (entity.getStudentId() == null ) {

				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

			} else {
				
				entity = studentService.update(entity);
				log.debug("updated student," + entity.toString());
				return new ResponseEntity<>(entity, HttpStatus.CREATED);
			}
			
		} catch (Exception e) {

			log.error("error while updating students,values," + entity.toString() + ",error," + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Student> delete(@PathVariable("id") UUID id) {

		try {
			log.debug("started deleting student,id, " + id);

			if (studentService.delete(id)) {
				log.trace("deleting studentbyId," + true);

				return new ResponseEntity<>(HttpStatus.OK);

			} else {
				log.trace("deleting studentbyId," + id + "," + false + ",not found");

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

		} catch (Exception e) {

			log.error("error while deleting student," + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<Student> findyById(@PathVariable("id") UUID id) {
		try {
			log.debug("finding student with id," + id);

			if (studentService.findbyId(id) != null) {

				Student c = studentService.findbyId(id);
				log.debug("found student with values," + c.toString());
				return new ResponseEntity<>(c, HttpStatus.OK);

			} else {
				log.debug("not found student with values id," + id);

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (NumberFormatException e) {
			log.error("error while finding student,id," + id + "," + e.toString());

			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {

			log.error("error while finding all student,id," + id + "," + e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping
	public ResponseEntity<List<Student>> findall() {

		try {
			log.debug("getting all students from database");
			List<Student> ls = studentService.findall();

			if (!ls.isEmpty()) {
				log.debug("students found size," + ls.size());

				// return ls;
				return new ResponseEntity<>(ls, HttpStatus.OK);

			} else {
				log.debug("students not found, datbase is empty");

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

		} catch (Exception e) {

			log.error("error while getting all students," + e.toString());
			// return null;
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * this function return the current timeStamp
	 * @return
	 */
	public java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
}
