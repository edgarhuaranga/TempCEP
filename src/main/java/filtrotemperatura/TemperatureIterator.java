package filtrotemperatura;




import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TemperatureIterator implements Iterator<Temperature>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Timestamp INITIAL_TIMESTAMP = Timestamp.valueOf("2019-01-01 00:00:00");
    private static final long SIX_MINUTES = 360000L;
    private final boolean bounded;
    private int index = 0;
    private long timestamp;
    private static List<Temperature> data = Arrays.asList(
            new Temperature(1L, 0L, 18.23),
            new Temperature(2L, 0L, 374.79),
            new Temperature(3L, 0L, 112.15),
            new Temperature(4L, 0L, 478.75),
            new Temperature(5L, 0L, 208.85),
            new Temperature(1L, 0L, 379.64),
            new Temperature(2L, 0L, 351.44),
            new Temperature(3L, 0L, 320.75),
            new Temperature(4L, 0L, 259.42),
            new Temperature(5L, 0L, 273.44),
            new Temperature(1L, 0L, 267.25),
            new Temperature(2L, 0L, 397.15),
            new Temperature(3L, 0L, 0.219),
            new Temperature(4L, 0L, 231.94),
            new Temperature(5L, 0L, 384.73),
            new Temperature(1L, 0L, 419.62),
            new Temperature(2L, 0L, 412.91),
            new Temperature(3L, 0L, 0.77),
            new Temperature(4L, 0L, 22.1),
            new Temperature(5L, 0L, 377.54),
            new Temperature(1L, 0L, 375.44),
            new Temperature(2L, 0L, 230.18),
            new Temperature(3L, 0L, 0.8),
            new Temperature(4L, 0L, 350.89),
            new Temperature(5L, 0L, 127.55),
            new Temperature(1L, 0L, 483.91),
            new Temperature(2L, 0L, 228.22),
            new Temperature(3L, 0L, 871.15),
            new Temperature(4L, 0L, 64.19),
            new Temperature(5L, 0L, 79.43),
            new Temperature(1L, 0L, 56.12),
            new Temperature(2L, 0L, 256.48),
            new Temperature(3L, 0L, 148.16),
            new Temperature(4L, 0L, 199.95),
            new Temperature(5L, 0L, 252.37),
            new Temperature(1L, 0L, 274.73),
            new Temperature(2L, 0L, 473.54),
            new Temperature(3L, 0L, 119.92),
            new Temperature(4L, 0L, 323.59),
            new Temperature(5L, 0L, 353.16),
            new Temperature(1L, 0L, 211.9),
            new Temperature(2L, 0L, 280.93),
            new Temperature(3L, 0L, 347.89),
            new Temperature(4L, 0L, 459.86),
            new Temperature(5L, 0L, 82.31),
            new Temperature(1L, 0L, 373.26),
            new Temperature(2L, 0L, 479.83),
            new Temperature(3L, 0L, 454.25),
            new Temperature(4L, 0L, 83.64),
            new Temperature(5L, 0L, 292.44));

    static TemperatureIterator bounded() {
        return new TemperatureIterator(true);
    }

    static TemperatureIterator unbounded() {
        return new TemperatureIterator(false);
    }

    private TemperatureIterator(boolean bounded) {
        this.bounded = bounded;
        this.timestamp = INITIAL_TIMESTAMP.getTime();
    }

    public boolean hasNext() {
        if (this.index < data.size()) {
            return true;
        } else if (!this.bounded) {
            this.index = 0;
            return true;
        } else {
            return false;
        }
    }

    public Temperature next() {
        Temperature transaction = (Temperature)data.get(this.index++);
        transaction.setTimestamp(this.timestamp);
        this.timestamp += 360000L;
        return transaction;
    }
}
