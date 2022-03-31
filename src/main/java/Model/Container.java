package Model;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Data
public class Container {

    private @Getter Map<String, Integer> dimensions = new HashMap<String, Integer>();
    private @Getter Integer volume;

    public Container() {
    }

    public Container(Integer l, Integer w, Integer h) {
        volume = l * w * h;
        dimensions.put("length", l);
        dimensions.put("width", w);
        dimensions.put("height", h);

    }


}
