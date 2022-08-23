package filtrotemperatura;

import org.apache.flink.streaming.api.functions.source.FromIteratorFunction;

import java.io.Serializable;
import java.util.Iterator;


public class TemperatureSource extends FromIteratorFunction<Temperature> {
    private static final long serialVersionUID = 1L;

    public TemperatureSource() {
        super(new TemperatureSource.RateLimitedIterator(TemperatureIterator.unbounded()));
    }

    private static class RateLimitedIterator<T> implements Iterator<T>, Serializable {
        private static final long serialVersionUID = 1L;
        private final Iterator<T> inner;

        private RateLimitedIterator(Iterator<T> inner) {
            this.inner = inner;
        }

        public boolean hasNext() {
            return this.inner.hasNext();
        }

        public T next() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var2) {
                throw new RuntimeException(var2);
            }

            return this.inner.next();
        }
    }
}
