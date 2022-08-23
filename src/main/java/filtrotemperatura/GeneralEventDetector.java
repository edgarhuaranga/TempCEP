package filtrotemperatura;

import filtrotemperatura.events.GeneralEvent;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.util.Collector;

import java.util.List;
import java.util.Map;

public class GeneralEventDetector extends PatternProcessFunction<GeneralEvent, Alerta> {

    @Override
    public void processMatch(Map<String, List<GeneralEvent>> pattern, Context context, Collector<Alerta> collector) throws Exception {

        System.out.println("HOLA = ");
        for (Map.Entry<String,List<GeneralEvent>> entry : pattern.entrySet()){
            System.out.println("Key = " + entry.getKey());
            List<GeneralEvent> eventos = entry.getValue();
            for (GeneralEvent evento : eventos) {
                System.out.println(evento);
                Alerta alert = new Alerta();
                alert.setId(evento.getSensorId());
                alert.setDescription(evento.getValue()+" ....");
                collector.collect(alert);
            }

        }

    }
}
