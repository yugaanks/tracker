package egen.tracker.repository;

import egen.tracker.entity.Alert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AlertRepositoryImpl implements AlertRepository {

	@PersistenceContext
	private EntityManager em;

	public void create_alert(Alert alert) {
		em.persist(alert);
		em.close();
	}

	public List<Alert> get_alerts_by_vin(String vin) {
		TypedQuery<Alert> query = em.createNamedQuery("Alert.get_alerts_by_vin", Alert.class);
		query.setParameter("paramVin", vin);
		return query.getResultList();
	}

	public List<Alert> getAllAlerts() {
		TypedQuery<Alert> query = em.createNamedQuery("Alert.getAllAlerts", Alert.class);
		return query.getResultList();
	}

}
