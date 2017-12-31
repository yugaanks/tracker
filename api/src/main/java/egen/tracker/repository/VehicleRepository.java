package egen.tracker.repository;

import egen.tracker.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {
	public void insert_vehicle(Vehicle vehicle);

	public void update_vehicle(Vehicle vehicle);

	public Vehicle get_vehicle_by_vin(String vin);

	public List<Vehicle> getAllVehicles();
}
