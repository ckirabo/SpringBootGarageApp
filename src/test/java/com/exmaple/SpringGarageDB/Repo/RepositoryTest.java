package com.exmaple.SpringGarageDB.Repo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.SpringGarageDB.SpingGarageDbApplication;
import com.example.SpringGarageDB.Model.VehicleModel;
import com.example.SpringGarageDB.Repository.VehicleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {SpingGarageDbApplication.class})
public class RepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private VehicleRepository myRepo;
	
	@Test
	public void retrieveByIdTest() {
		VehicleModel model = new VehicleModel(); 
		model.setBrand("Toyota");
		model.setvehicleType("Car");
		model.setLicenceNo("YAHDSA0");
		entityManager.persist(model);
		entityManager.flush();
		assertTrue(myRepo.findById(model.getID()).isPresent());
	}
	
	
}
  