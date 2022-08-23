package filtrotemperatura;

import java.util.Objects;

public class Temperature {
    private long sensorId;
    private long timestamp;
    private double value;

    public Temperature() {
    }

    public Temperature(long sensorId, long timestamp, double value) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return sensorId == that.sensorId && timestamp == that.timestamp && Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, timestamp, value);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "sensorId=" + sensorId +
                ", timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}
