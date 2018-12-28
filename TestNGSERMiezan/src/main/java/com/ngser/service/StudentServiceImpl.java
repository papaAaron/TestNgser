/**
 * 
 */
package com.ngser.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngser.dao.StudentDao;
import com.ngser.entities.Student;

/**
 * @author ars
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentDao studentDao;

	@Override
	public Student save(Student entity) {
		return studentDao.save(entity);
	}

	@Override
	public Student update(Student entity) {
		return studentDao.save(entity);
	}

	@Override
	public Student findbyId(UUID id) {

		if (studentDao.existsById(id)) {
			return studentDao.getOne(id);

		} else {

			return null;
		}	}

	


	@Override
	public List<Student> findall() {
		return studentDao.findAll();
	}

	@Override
	public Boolean delete(UUID id) {
		try {
			log.trace("student by id," + id);
			studentDao.deleteById(id);
			log.trace("deleting hard student by id," + true);
			return true;
		} catch (Exception e) {

			log.error("error while deleting student,"+id+"," + e.toString());
			return false;
		}	}

	@Override
	public Student findbyname(String name) {
		return studentDao.findByStudentFirstNameIgnoreCase(name);
	}

	
}
