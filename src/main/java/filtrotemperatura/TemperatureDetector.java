package filtrotemperatura;

import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;

public class TemperatureDetector extends KeyedProcessFunction<Long, Temperature, Alerta> {
    private static final long serialVersionUID = 1L;
    private static final double TEMP_LIMIT = 40.0;

    @Override
    public void processElement(
            Temperature transaction,
            Context context,
            Collector<Alerta> collector) throws Exception {

        if (transaction.getValue() > TEMP_LIMIT) {
            Alerta alert = new Alerta();
            alert.setId(transaction.getSensorId());
            alert.setDescription(transaction.getValue()+" ....");
            collector.collect(alert);
        }

    }
}
