package egen.tracker.controller;

import egen.tracker.entity.Vehicle;
import egen.tracker.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.io")
@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicle_service;

	@RequestMapping(method = RequestMethod.GET, value = "/{vin}")
	public Vehicle getVehicleByVin(@PathVariable("vin") String vin) {
		return vehicle_service.getVehicleByVin(vin);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void upsertVehicles(@RequestBody List<Vehicle> vehicle) {
		vehicle_service.upsertVehicle(vehicle);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> getAllVehicles() {

		return vehicle_service.getAllVehicles();
	}

}
