package filtrotemperatura;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TemperatureIterator implements Iterator<Temperature>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Timestamp INITIAL_TIMESTAMP = Timestamp.valueOf("2019-01-01 00:00:00");
    private final boolean bounded;
    private int index = 0;
    private long timestamp;
    private static List<Temperature> data = Arrays.asList(
            new Temperature(1L, 0L, 18.23),
            new Temperature(2L, 0L, 37.79),
            new Temperature(3L, 0L, 11.15),
            new Temperature(4L, 0L, 47.75),
            new Temperature(5L, 0L, 20.85),
            new Temperature(1L, 0L, 37.64),
            new Temperature(2L, 0L, 35.44),
            new Temperature(3L, 0L, 32.75),
            new Temperature(4L, 0L, 25.42),
            new Temperature(5L, 0L, 27.44),
            new Temperature(1L, 0L, 26.25),
            new Temperature(2L, 0L, 39.15),
            new Temperature(3L, 0L, 0.219),
            new Temperature(4L, 0L, 23.94),
            new Temperature(5L, 0L, 38.73),
            new Temperature(1L, 0L, 41.62),
            new Temperature(2L, 0L, 41.91),
            new Temperature(3L, 0L, 0.77),
            new Temperature(4L, 0L, 22.1),
            new Temperature(5L, 0L, 37.54),
            new Temperature(1L, 0L, 37.44),
            new Temperature(2L, 0L, 23.18),
            new Temperature(3L, 0L, 0.8),
            new Temperature(4L, 0L, 35.89),
            new Temperature(5L, 0L, 12.55),
            new Temperature(1L, 0L, 48.91),
            new Temperature(2L, 0L, 22.22),
            new Temperature(3L, 0L, 87.15),
            new Temperature(4L, 0L, 64.19),
            new Temperature(5L, 0L, 79.43),
            new Temperature(1L, 0L, 56.12),
            new Temperature(2L, 0L, 25.48),
            new Temperature(3L, 0L, 14.16),
            new Temperature(4L, 0L, 19.95),
            new Temperature(5L, 0L, 25.37),
            new Temperature(1L, 0L, 27.73),
            new Temperature(2L, 0L, 47.54),
            new Temperature(3L, 0L, 11.92),
            new Temperature(4L, 0L, 32.59),
            new Temperature(5L, 0L, 35.16),
            new Temperature(1L, 0L, 21.9),
            new Temperature(2L, 0L, 28.93),
            new Temperature(3L, 0L, 34.89),
            new Temperature(4L, 0L, 45.86),
            new Temperature(5L, 0L, 82.31),
            new Temperature(1L, 0L, 37.26),
            new Temperature(2L, 0L, 47.83),
            new Temperature(3L, 0L, 45.25),
            new Temperature(4L, 0L, 83.64),
            new Temperature(5L, 0L, 29.44));

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
