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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
		myRepo.deleteAll();
	}
	
	@Test
	public void findAndRetrieveVehicleFromDatabase() 
		throws Exception {
		VehicleModel model = new VehicleModel(); 
		model.setBrand("Benz");
		model.setvehicleType("Car");
		model.setLicenceNo("93HEAD");
		
		
			myRepo.save(model);
			mvc.perform(get("/api/vehicles")
					.contentType(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			        		.andExpect(jsonPath("$[0].brand", is("Benz")));
			
		}
	
	@Test 
	public void addVehicleToDBStest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/newVehicle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"brand\" : \"Mini\" ,\"vehicleType\": \"van\", \"licenceNo\" : \"HSAIDHA\" }"))
		        .andExpect(status().isOk())
		                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		                .andExpect(jsonPath("$.brand", is("Mini")));
	}
	

	
	@Test 
	public void updateVehicleInDBTest() throws Exception{
		
		VehicleModel model3 = new VehicleModel(); 
		model3.setBrand("BMW");
		model3.setvehicleType("Car");
		model3.setLicenceNo("93T8AD");
		
		myRepo.save(model3);
		mvc.perform(MockMvcRequestBuilders.put("/api/updateVehicle/"+model3.getID())
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"brand\" : \"BMW\" ,\"vehicleType\": \"Car\", \"licenceNo\" : \"65b5re\" }"))
				.andExpect(status().isOk())
				           .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				           .andExpect(jsonPath("$.licenceNo", is("65b5re")));
				        		   		       
				        		   
				             
	}
	
	@Test 
	public void deleteVehicleinDBTest() throws Exception{
		VehicleModel model4 = new VehicleModel(); 
		model4.setBrand("BMW");
		model4.setvehicleType("Car");
		model4.setLicenceNo("93T8AD");
		
		myRepo.save(model4);
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/vehicle/"+model4.getID())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}
	
	
	
	
	
	
}
