package egen.tracker.service;

import egen.tracker.entity.Vehicle;
import egen.tracker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	public void upsertVehicle(List<Vehicle> vehicles) {
		for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.toString());
			Vehicle existing = vehicleRepository.get_vehicle_by_vin(vehicle.getVin());
			if (existing == null)
				vehicleRepository.insert_vehicle(vehicle);
			else
				vehicleRepository.update_vehicle(vehicle);
		}
	}

	public Vehicle getVehicleByVin(String vin) {
		Vehicle existing = vehicleRepository.get_vehicle_by_vin(vin);
		if (existing == null) {
			try {
				throw new Exception("The Vehicle with vin: " + vin + "does not exist.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vehicleRepository.get_vehicle_by_vin(vin);
	}

	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.getAllVehicles();
	}
}
