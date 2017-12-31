package egen.tracker.repository;

import egen.tracker.entity.Reading;

import java.util.List;

public interface ReadingRepository {
	public void insertReadings(Reading reading);

	public List<Reading> getReadingsByVin(String vin);

	public List<Reading> getAllReadings();
}
