/**
 * 
 */
package com.ngser.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ngser.entities.Student;

/**
 * @author ars
 *
 */
@Repository
public interface StudentDao extends JpaRepository<Student, UUID> {
	

	Student findByStudentFirstNameIgnoreCase(String name);
}
