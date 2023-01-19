/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package histogram;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jjque
 */
public class HistogramBuilder<T> implements Histogram{

    private final Map<T, Integer> map = new HashMap<T, Integer>();
    
    @Override
    public Integer get(Object key) {
        return this.map.get(key);
    }

    @Override
    public Set keySet() {
        return this.map.keySet();
    }
    
    public void increment(T key) {
        this.map.put(key, this.map.containsKey(key) ? this.map.get(key) + 1 : 1);
    }
    
}
