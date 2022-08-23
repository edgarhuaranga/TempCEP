package filtrotemperatura;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;


public class TemperatureDetectionJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Temperature> transactions = env
                .addSource(new TemperatureSource())
                .name("datos");

        DataStream<Alerta> alerts = transactions
                .keyBy(Temperature::getSensorId)
                .process(new TemperatureDetector())
                .name("temperature-detector");

        alerts
                .addSink(new AlertaSink())
                .name("send-alerts");

        env.execute("Fraud Detection");
    }
}
