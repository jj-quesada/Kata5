package histogram;

import java.util.Set;

public interface Histogram<T> {
 
    public Integer get(T key);
    public Set<T> keySet();
    
}
