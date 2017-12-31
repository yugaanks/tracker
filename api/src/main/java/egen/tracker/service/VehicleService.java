package egen.tracker.service;

import egen.tracker.entity.Vehicle;

import java.util.List;

public interface VehicleService {
	public void upsertVehicle(List<Vehicle> vehicle);

	public Vehicle getVehicleByVin(String vin);

	public List<Vehicle> getAllVehicles();
}
