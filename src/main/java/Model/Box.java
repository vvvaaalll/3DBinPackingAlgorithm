package Model;

import ch.qos.logback.classic.Logger;
import lombok.Data;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Data
public class Box {

    private String type;
    private Map<String, Integer> dimension = new HashMap<String, Integer>();
    private Integer volume;

    Logger logger = (Logger) LoggerFactory.getLogger(Box.class);

    public Box(String boxType, Integer l, Integer w, Integer h) {
        type = boxType;
        volume = l * w * h;
        dimension.put("length", l);
        dimension.put("width", w);
        dimension.put("height", h);

    }

    public Box(Integer l, Integer w, Integer h) {
        volume = l * w * h;
        dimension.put("length", l);
        dimension.put("width", w);
        dimension.put("height", h);

    }


    public static ArrayList<Box> GenerateBoxes(String boxType, Integer nOfBoxes, Integer l, Integer w, Integer h){
        ArrayList<Box> boxes = new ArrayList<Box>();

        for(int i=0; i<nOfBoxes; i++) {
            boxes.add(new Box(boxType, l, w, h));
        }

        return boxes;
    }

    /*
    *       l w h
    *       w h l
    *       h l w
    * */

    public Box RotateBox() {

        logger.info("box before rotating {}", getDimension());

        Set<String> keySet = this.dimension.keySet();
        String[] sides = keySet.toArray(new String[3]);
        dimension.put(sides[1],
                dimension.put(sides[2], dimension.get(sides[1])));
        dimension.put(sides[0],
                dimension.put(sides[2], dimension.get(sides[0])));
        logger.info("box after rotating {}", getDimension());
        return this;
    }



}
