package egen.tracker.service;

import egen.tracker.entity.Reading;
import egen.tracker.entity.Vehicle;
import egen.tracker.repository.ReadingRepository;
import egen.tracker.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	private ReadingRepository readingRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private AlertService alertService;

	public void insertReadings(Reading reading) {
		Vehicle existing = vehicleRepository.get_vehicle_by_vin(reading.getVin());
		if (existing == null) {
			try {
				throw new Exception("Readings for a Vehicle with vin: " + reading.getVin() + "Does not exist.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			alertService.engine_rpm_alert(reading);
			alertService.tire_pressure_alert(reading);
			alertService.fuel_alert(reading);
			alertService.engine_alert(reading);
			readingRepository.insertReadings(reading);
		}
	}

	public List<Reading> getReadingsByVin(String vin) {
		Vehicle existing = vehicleRepository.get_vehicle_by_vin(vin);
		if (existing == null) {
			try {
				throw new Exception("A Vehicle with vin: " + vin + "does not exist.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readingRepository.getReadingsByVin(vin);
	}

	public List<Reading> getAllReadings() {
		return readingRepository.getAllReadings();
	}
}
