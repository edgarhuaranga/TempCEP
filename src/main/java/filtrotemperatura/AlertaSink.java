package filtrotemperatura;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertaSink implements SinkFunction<Alerta> {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(AlertaSink.class);

    public AlertaSink() {
    }

    public void invoke(Alerta value, SinkFunction.Context context) {
        LOG.info(value.toString());
    }
}
