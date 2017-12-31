package egen.tracker.service;

import egen.tracker.entity.Reading;

import java.util.List;

public interface ReadingService {

	public void insertReadings(Reading reading);

	public List<Reading> getReadingsByVin(String vin);

	public List<Reading> getAllReadings();
}
