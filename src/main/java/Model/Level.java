package Model;

import java.util.ArrayList;

public class Level {

    private ArrayList<Box> packedBoxes;

    public Level(){

        packedBoxes = new ArrayList<Box>();

    }

    public ArrayList<Box> GetPackedBoxes() {
        return packedBoxes;
    }

    public void Add(Box box){

        this.packedBoxes.add(box);


    }


}
