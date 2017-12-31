package egen.tracker.controller;

import egen.tracker.entity.Reading;
import egen.tracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://mocker.egen.io")
@RestController
@RequestMapping(value = "/readings")
public class ReadingController {

	@Autowired
	private ReadingService reading_service;

	@RequestMapping(method = RequestMethod.POST)
	public void insertReadings(@RequestBody Reading reading) {
		reading_service.insertReadings(reading);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{vin}")
	public List<Reading> getReadingsByVin(@PathVariable("vin") String vin) {
		return reading_service.getReadingsByVin(vin);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Reading> getAllReadings() {
		return reading_service.getAllReadings();
	}
}
