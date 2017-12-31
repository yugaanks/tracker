package egen.tracker.service;

import egen.tracker.entity.Alert;
import egen.tracker.entity.Reading;
import egen.tracker.entity.Tires;
import egen.tracker.entity.Vehicle;
import egen.tracker.repository.AlertRepository;
import egen.tracker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

	@Autowired
	VehicleRepository vehicle_repository;

	@Autowired
	AlertRepository alert_repository;

	public void engine_rpm_alert(Reading reading) {

		String vin = reading.getVin();
		Vehicle vehicle = vehicle_repository.get_vehicle_by_vin(vin);
		double engineRpm = reading.getEngineRpm();
		double readLineRpm = vehicle.getRedlineRpm();
		if (engineRpm > readLineRpm) {
			Alert alert = new Alert();
			alert.setVin(reading.getVin());
			alert.setPriority("high");
			alert.setAlertType("engine");
			alert_repository.create_alert(alert);
		}
	}

	public void fuel_alert(Reading reading) {
		String vin = reading.getVin();
		Vehicle vehicle = vehicle_repository.get_vehicle_by_vin(vin);
		double fuelVolume = reading.getFuelVolume();
		double maxfuelVolume = vehicle.getMaxFuelVolume();
		if (fuelVolume < maxfuelVolume * .10) {
			Alert alert = new Alert();
			alert.setVin(reading.getVin());
			alert.setPriority("medium");
			alert.setAlertType("fuel");
			alert_repository.create_alert(alert);
		}
	}

	public void tire_pressure_alert(Reading reading) {
		Tires tires = reading.getTires();
		com.fasterxml.jackson.databind.ObjectMapper m = new com.fasterxml.jackson.databind.ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map_pressure = m.convertValue(tires, Map.class);

		for (Map.Entry<String, Object> entry : map_pressure.entrySet()) {
			int pressure = Integer.parseInt(entry.getValue().toString());
			if (pressure < 32 || pressure > 36) {
				Alert alert = new Alert();
				alert.setVin(reading.getVin());
				alert.setPriority("low");
				alert.setAlertType("tire_pressure");
				alert_repository.create_alert(alert);
			}
		}
	}

	public void engine_alert(Reading reading) {
		if (reading.isEngineCoolantLow() || reading.isCheckEngineLightOn()) {
			Alert alert = new Alert();
			alert.setVin(reading.getVin());
			alert.setPriority("low");
			alert.setAlertType("engine");
			alert_repository.create_alert(alert);
		}
	}

	public List<Alert> get_alerts_by_vin(String vin) {
		Vehicle vehicle = vehicle_repository.get_vehicle_by_vin(vin);
		if (vehicle == null) {
			try {
				throw new Exception("The Vehicle does not exist.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return alert_repository.get_alerts_by_vin(vin);
	}

	public List<Alert> getAllAlerts() {
		return alert_repository.getAllAlerts();
	}

}
