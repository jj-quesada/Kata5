package kata5;

import histogram.Histogram;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DecoratedHistogram implements Histogram {

    private final Map<String, Integer> map = new HashMap<>();
    
    @Override
    public Integer get(Object key) {
        return this.map.get(key);
    }

    @Override
    public Set keySet() {
        return this.map.keySet();
    }

    public void decorate(Histogram histogram) {
        for(Object key : histogram.keySet()){
        
            if((int)key < 10){
                this.map.put("0" + String.valueOf(key) + ":00", histogram.get(key));
            } else {
                this.map.put(String.valueOf(key) + ":00", histogram.get(key));
            }
        }
    }
    
}
