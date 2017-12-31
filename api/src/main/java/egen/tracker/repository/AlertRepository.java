package egen.tracker.repository;

import egen.tracker.entity.Alert;

import java.util.List;

public interface AlertRepository {
	public void create_alert(Alert alert);

	public List<Alert> get_alerts_by_vin(String vin);

	public List<Alert> getAllAlerts();
}
