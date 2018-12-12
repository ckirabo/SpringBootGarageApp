package com.example.SpringGarageDB.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringGarageDB.Execption.ResourceNotFoundException;
import com.example.SpringGarageDB.Model.VehicleModel;
import com.example.SpringGarageDB.Repository.VehicleRepository;

@RestController
@RequestMapping("/api")
public class VehicleController {

	@Autowired
	VehicleRepository myRepository;
	
	//method to create a vehicle
		@PostMapping("/newVehicle")
		public VehicleModel createVehicle(@Valid @RequestBody VehicleModel mSDM) {
			return myRepository.save(mSDM);
		}
		
	//method to get a vehicle 
	@GetMapping("vehicle/(id)")
	public VehicleModel getVehicleID(@PathVariable (value = "id") Long vehicleID) {
		return myRepository.findById(vehicleID).orElseThrow(()->
		new ResourceNotFoundException ("VehicleModel", "id", vehicleID));
	}
	
	//method to get all vehicles
		@GetMapping("/vehicles")
		public List<VehicleModel> getAllVehicles(){
			return myRepository.findAll();
		}
		
	//method to update a vehicle 
		@PutMapping("/vehicle/(id)")
		public VehicleModel updateVehicle(@PathVariable (value = "id") Long vehicleID, 
				@Valid @RequestBody VehicleModel vehicleDetails) {
			
			VehicleModel mSDM = myRepository.findById(vehicleID).orElseThrow(() 
					-> new ResourceNotFoundException("Vehicle", "id", vehicleID));
			
			mSDM.setBrand(vehicleDetails.getBrand());
			mSDM.setvehicleType(vehicleDetails.getVehicleType());
			mSDM.setLicenceNo(vehicleDetails.getLicenceNo());
			
			VehicleModel updateData = myRepository.save(mSDM);
			return updateData;
		}
	
	 //method to remove a vehicle 
		@DeleteMapping("/vehicle/(id)")
		public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Long vehicleID){
			VehicleModel mSDM = myRepository.findById(vehicleID).orElseThrow(() 
					-> new ResourceNotFoundException("Vehicle", "id", vehicleID));
			
			myRepository.delete(mSDM);
			return ResponseEntity.ok().build();
		}
		
		
		//method to delete vehicle by type
		/*
		public List<VehicleModel> findAllVehiclesWithType(String vehicleType){	
	
			return myRepository.findByVehicleType(vehicleType);
			
		}
		
		@DeleteMapping("/Vehicle/(vehicleType)")
		public ResponseEntity<?> removeByVehicleType(@PathVariable(value = "vehicleType") String vehicleType){
			
			List<VehicleModel> vehicleTypeList = myRepository.findByVehicleType(vehicleType);
			
			for( int i = 0; i < vehicleTypeList.size(); i++ ) {
				// myRepository.delete(vehicleTypeList.remove(i)); 
				//myRepository.delete(vehicleTypeList.get(i));
				//vehicleTypeList.remove(i);
				
			}
			
			return ResponseEntity.ok().build(); 
			
		}*/
		
		
}
