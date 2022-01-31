import Model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackingTests {

    @Test
    public void PackingTest1(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 2, 1, 1));
        Container container = new Container(4,3,3);

        Packing packing = new Packing(boxes, container);
        while(!packing.GetBoxes().isEmpty()) {
            packing.PackLevel();
        }
        assertEquals(6, packing.GetLevels().get(0).GetPackedBoxes().size());
        assertEquals(4, packing.GetLevels().get(1).GetPackedBoxes().size());
                assertEquals(10, packing.CountOfBoxesFit());


    }
    @Test
    public void PackingTest2(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 1, 1, 1));
        Container container = new Container(2,2,3);

        Packing packing = new Packing(boxes, container);
        while(!packing.GetBoxes().isEmpty()) {
            packing.PackLevel();
        }

        assertEquals(4, packing.GetLevels().get(0).GetPackedBoxes().size());
        assertEquals(4, packing.GetLevels().get(1).GetPackedBoxes().size());
        assertEquals(2, packing.GetLevels().get(2).GetPackedBoxes().size());
        assertEquals(10, packing.CountOfBoxesFit());


    }
    @Test
    public void PackingTest3(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 1, 1, 1));
        Container container = new Container(3,3,2);

        Packing packing = new Packing(boxes, container);
        while(!packing.GetBoxes().isEmpty()) {
            packing.PackLevel();
        }

        assertEquals(9, packing.GetLevels().get(0).GetPackedBoxes().size());
        assertEquals(1, packing.GetLevels().get(1).GetPackedBoxes().size());
        assertEquals(10, packing.CountOfBoxesFit());



    }



    @Test
    public void PackingTest4(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 1, 2, 6));
        boxes.addAll(Box.GenerateBoxes("2", 10, 2, 3, 4));
        Container container = new Container(8,7,15);

        boolean canFit = false;

        Packing packing = new Packing(boxes, container);


        while(!packing.GetBoxes().isEmpty()) {
            packing.PackLevel();
        }

        assertEquals(20, packing.CountOfBoxesFit());


    }

    @Test
    public void PackingTest5(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 1, 1, 1));
        Container container = new Container(0,0,0);

        Packing packing = new Packing(boxes, container);
        while(!(!packing.GetBoxes().isEmpty() || !(packing.HeightOfPackedStack() >= container.GetDimensions().get("height")))) {
            packing.PackLevel();
        }

        assertEquals(0, packing.CountOfBoxesFit());


    }

    @Test
    public void PackingTest6(){
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.addAll(Box.GenerateBoxes("1", 10, 5, 3, 4));
        boxes.addAll(Box.GenerateBoxes("2", 5, 2, 4, 3));
        Container container = new Container(20,20,20);

        Packing packing = new Packing(boxes, container);
        while(!packing.GetBoxes().isEmpty() ) {
            packing.PackLevel();
        }

        assertEquals(15, packing.CountOfBoxesFit());


    }

}
