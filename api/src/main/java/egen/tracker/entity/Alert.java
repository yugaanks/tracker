package egen.tracker.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Alert.get_alerts_by_vin", query = "SELECT alert from Alert alert where alert.vin=:paramVin"),
        @NamedQuery(name = "Alert.getAllAlerts", query = "SELECT alert from Alert alert"),
})
public class Alert {

    @Id
    private String id;
    private String vin;
    private String priority;
    private String alertType;

    public String getId() {
        return id;
    }

    public Alert() {
        this.id = UUID.randomUUID().toString();
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}
