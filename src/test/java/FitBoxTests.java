import Model.Box;
import Model.Container;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class FitBoxTests {


    @Test
    public void TestFitBox1(){
        //Arrange

        Container container = new Container(5,10,20);
        Box box = new Box(5,10,20);

        Packing packing = new Packing(box, container);
        //Act
        Boolean result = packing.FitBox(container, box);
        //Assert
        assertEquals(true, result);

    }

    @Test
    public void TestFitBox2(){
        //Arrange

        Container container = new Container(10,20,5);
        Box box = new Box(5,10,20);

        Packing packing = new Packing(box, container);
        //Act
        Boolean result = packing.FitBox(container, box);
        //Assert
        assertEquals(true, result);

    }

    @Test
    public void TestFitBox3(){
        //Arrange

        Container container = new Container(20,5,10);
        Box box = new Box(5,10,20);

        Packing packing = new Packing(box, container);
        //Act
        Boolean result = packing.FitBox(container, box);
        //Assert
        assertEquals(true, result);

    }

    @Test
    public void TestFitBox4(){
        //Arrange

        Container container = new Container(5,5,5);
        Box box = new Box(5,10,20);

        Packing packing = new Packing(box, container);
        //Act
        Boolean result = packing.FitBox(container, box);
        //Assert
        assertEquals(false, result);

    }

    @Test
    public void TestFitBox5(){
        //Arrange

        Container container = new Container(0,0,0);
        Box box = new Box(5,10,20);

        Packing packing = new Packing(box, container);
        //Act
        Boolean result = packing.FitBox(container, box);
        //Assert
        assertEquals(false, result);

    }

}
