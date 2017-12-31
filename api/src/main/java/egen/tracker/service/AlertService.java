package egen.tracker.service;

import egen.tracker.entity.Alert;
import egen.tracker.entity.Reading;

import java.util.List;

public interface AlertService {
	public void engine_rpm_alert(Reading reading);

	public void fuel_alert(Reading reading);

	public void tire_pressure_alert(Reading reading);

	public void engine_alert(Reading reading);

	public List<Alert> get_alerts_by_vin(String vin);

	public List<Alert> getAllAlerts();
}
