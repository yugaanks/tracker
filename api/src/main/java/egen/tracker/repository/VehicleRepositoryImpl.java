package egen.tracker.repository;

import egen.tracker.entity.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class VehicleRepositoryImpl implements VehicleRepository {

	@PersistenceContext
	private EntityManager em;

	public void insert_vehicle(Vehicle vehicle) {
		em.persist(vehicle);
		em.close();
	}

	public void update_vehicle(Vehicle vehicle) {
		em.merge(vehicle);
		em.close();
	}

	public Vehicle get_vehicle_by_vin(String vin) {
		return em.find(Vehicle.class, vin);
	}

	public List<Vehicle> getAllVehicles() {
		TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.getAllVehicles", Vehicle.class);
		return query.getResultList();
	}
}
