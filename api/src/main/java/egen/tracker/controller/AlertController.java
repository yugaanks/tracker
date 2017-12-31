package egen.tracker.controller;

import egen.tracker.entity.Alert;
import egen.tracker.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

	@Autowired
	AlertService alert_service;

	@RequestMapping(method = RequestMethod.GET, value = "/{vin}")
	public List<Alert> getAlertsByVin(@PathVariable("vin") String vin) {
		return alert_service.get_alerts_by_vin(vin);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Alert> getAllAlerts() {
		return alert_service.getAllAlerts();
	}
}
