package Model;

import java.util.HashMap;
import java.util.Map;

public class Container {

    private Map<String, Integer> dimensions = new HashMap<String, Integer>();
    private Integer volume;

    public Container() {
    }

    public Container(Integer l, Integer w, Integer h) {
        volume = l * w * h;
        dimensions.put("length", l);
        dimensions.put("width", w);
        dimensions.put("height", h);

    }

    public Map<String, Integer> GetDimensions() {
        return dimensions;
    }

    public Integer GetVolume() {
        return volume;
    }

    public void SetDimension(Map<String, Integer> dimension) {
        this.dimensions = dimension;
    }

    public void SetVolume(Integer volume) {
        this.volume = volume;
    }
}
