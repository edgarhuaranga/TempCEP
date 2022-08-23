package filtrotemperatura;

import filtrotemperatura.events.GeneralEvent;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;


import java.util.List;
import java.util.Map;

import static org.apache.flink.cep.pattern.Pattern.begin;


public class TemperatureDetectionJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /*  DataStream<Temperature> transactions = env
                .addSource(new TemperatureSource())
                .name("datos");
         */
        DataStream<String> data = env.socketTextStream("127.0.0.1", 9999);
        /*DataStream<Temperature> transactions = data.map(new MapFunction<String, Temperature>() {
            @Override
            public Temperature map(String s) throws Exception {
                System.out.println(s);
                String[] data = s.split(",");
                long id = Long.parseLong(data[0]);
                long timestamp = Long.parseLong(data[1]);
                double value = Double.parseDouble(data[2]);
                return new Temperature(id, timestamp, value);
            }
        });*/

        DataStream<GeneralEvent> input = data.map(new MapFunction<String, GeneralEvent>() {
            @Override
            public GeneralEvent map(String s) throws Exception {
                System.out.println(s);
                String[] data = s.split(",");
                long id = Long.parseLong(data[0]);
                long timestamp = Long.parseLong(data[1]);
                double value = Double.parseDouble(data[2]);
                return new GeneralEvent(id, timestamp, value);
            }
        });

        Pattern<GeneralEvent,? > pattern = Pattern.<GeneralEvent>
                begin("inicio").where(
                    new SimpleCondition<GeneralEvent>() {
                        @Override
                        public boolean filter(GeneralEvent event) {
                            return event.getValue() > 40.0;
                        }
                    }
                ).next("middle").where(
                new SimpleCondition<GeneralEvent>() {
                    @Override
                    public boolean filter(GeneralEvent subEvent) {
                        return subEvent.getValue() > 50.0;
                    }
                }
        );

        PatternStream<GeneralEvent> patternStream = CEP.pattern(input, pattern).inProcessingTime();;

        DataStream<Alerta> alerts = patternStream.select(new PatternSelectFunction<GeneralEvent, Alerta>() {
            @Override
            public Alerta select(Map<String, List<GeneralEvent>> map) throws Exception {
                System.out.println("*--------------------------------*");
                return createAlert(map);
            }

            private Alerta createAlert(Map<String, List<GeneralEvent>> map) {
                Alerta alert = new Alerta();
                for (Map.Entry<String,List<GeneralEvent>> entry : map.entrySet()){
                    System.out.println("Key = " + entry.getKey());
                    List<GeneralEvent> eventos = entry.getValue();
                    for (GeneralEvent evento : eventos) {
                        System.out.println(evento);
                        alert.setId(evento.getSensorId());
                        alert.setDescription(evento.getValue()+" ....");
                    }
                }
                return alert;
            }
        });

        env.execute("Temperature Detection");

    }
}
