package com.example.SpringGarageDB.Integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.SpringGarageDB.Model.VehicleModel;
import com.example.SpringGarageDB.Repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationTest {

	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired 
	private VehicleRepository myRepo;
	
	@Before
	public void clearDB() {
		//myRepo.deleteAll();
	}
	
	@Test
	public void findAndRetrieveVehicleFromDatabase() 
		throws Exception {
		VehicleModel model = new VehicleModel(); 
		model.setBrand("Rolce-Royce");
		model.setvehicleType("Car");
		model.setLicenceNo("FHYGFF");
		
		
			myRepo.save(model);
			mvc.perform(get("/api/vehicles")
					.contentType(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			        .andExpect(content()
			        		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			        		.andExpect(jsonPath("$[0].brand", is("Rolce-Royce")));
			
		
		}
	
	
}
