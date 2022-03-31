package Model;

import lombok.Getter;

import java.util.ArrayList;

public class Level {

    private @Getter ArrayList<Box> packedBoxes;

    public Level(){

        packedBoxes = new ArrayList<Box>();

    }



    public void Add(Box box){

        this.packedBoxes.add(box);


    }


}
