package egen.tracker.repository;

import egen.tracker.entity.Reading;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ReadingRepositoryImpl implements ReadingRepository {

	@PersistenceContext
	private EntityManager em;

	public void insertReadings(Reading reading) {
		em.persist(reading);
		em.close();
	}

	public List<Reading> getReadingsByVin(String vin) {
		TypedQuery<Reading> query = em.createNamedQuery("Reading.getReadingByVin", Reading.class);
		query.setParameter("paramVin", vin);
		return query.getResultList();
	}

	public List<Reading> getAllReadings() {
		TypedQuery<Reading> query = em.createNamedQuery("Reading.getAllReadings", Reading.class);
		return query.getResultList();
	}
}
