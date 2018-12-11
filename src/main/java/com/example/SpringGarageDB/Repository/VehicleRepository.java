package com.example.SpringGarageDB.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringGarageDB.Model.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {
	
	public List<VehicleModel> findByVehicleType(String vehicleType);
	
}
