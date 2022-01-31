import Model.*;
import org.jetbrains.annotations.NotNull;
//import Model.Container;
//import Model.Level;

import java.util.ArrayList;

public class Packing {

    private ArrayList<Box> boxes = new ArrayList<Box>();
    private Container container = new Container();
    private ArrayList<Level> levels = new ArrayList<Level>();

    public Packing(ArrayList<Box> boxes, Container container) {
        this.boxes = boxes;
        this.container = container;
    }

    public Packing(Box box, Container container) {
        this.boxes.add(box);
        this.container = container;
    }

    public ArrayList<Box> GetBoxes() {
        return boxes;
    }

    public ArrayList<Level> GetLevels() {
        return levels;
    }

    public double GetPercentageOfUnusedContainer(){
        double percent=1;
        double sumOfBoxVolumes = 0;
        double containerVolume = container.GetVolume();

        for (Level level : levels) {
            for (Box box : level.GetPackedBoxes()) {
                sumOfBoxVolumes += box.GetVolume();
            }

        }

        return percent;
    }

    public boolean TryToFit(@NotNull Container freeSpace, @NotNull Box box) {
        if (freeSpace.GetDimensions().get("length") >= box.GetDimension().get("length")
                && freeSpace.GetDimensions().get("width") >= box.GetDimension().get("width")
                && freeSpace.GetDimensions().get("height") >= box.GetDimension().get("height")
        ) return true;

        return false;
    }

    public boolean FitBox(Container tempSpace, Box box) {
        if (TryToFit(tempSpace, box)) return true;
        else {
            for (int i = 0; i < 3; i++) {
                if (TryToFit(tempSpace, box.RotateBox())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int CountOfBoxesFit() {
            int count = 0;

            for (Level level : levels) {
                for (Box box : level.GetPackedBoxes()) {
                    count++;
                }

            }

            return count;
        }

        //TODO: Figure a way to get height of packed stack(levels possibly not the same height/boxes placed over smaller ones are "floating")
    public int HeightOfPackedStack () {
            int height = 0;
            int maxBoxHeight = 0;
            if(levels!=null)
            for (Level level : levels) {
                if (level != null && !level.GetPackedBoxes().isEmpty()) {
                    maxBoxHeight = level.GetPackedBoxes().get(0).GetDimension().get("height");
                    for (Box box : level.GetPackedBoxes()) {
                        if (box.GetDimension().get("height") >= maxBoxHeight)
                            maxBoxHeight = box.GetDimension().get("height");
                    }

                    height += maxBoxHeight;
                }
            }
        return height;
        }

    public void PackLevel() {

        Box biggestBox = null;
        int surfaceAreaOfBiggestBox = 0;
        Level level = new Level();
        levels.add(level);

        if (HeightOfPackedStack() >= container.GetDimensions().get("height")) return;

        Container packLevel = new Container(container.GetDimensions().get("length"),
                container.GetDimensions().get("width"),
                container.GetDimensions().get("height") - HeightOfPackedStack());


        for (Box box : boxes) {

            int surfaceArea = box.GetDimension().get("length") * box.GetDimension().get("width");

            if (surfaceArea > surfaceAreaOfBiggestBox) {
                surfaceAreaOfBiggestBox = surfaceArea;
                    if(FitBox(packLevel, box)){
                        biggestBox = box;
                    }
            }

        }
        if(biggestBox != null){
            level.Add(biggestBox);
            boxes.remove(biggestBox);
        }

        if (boxes.isEmpty()) return;


        if (biggestBox == null) {
            FillSpace(
                    new Container(packLevel.GetDimensions().get("length"),
                            packLevel.GetDimensions().get("width"),
                            packLevel.GetDimensions().get("height")),
                    level
            );
        } else {
            int packLevelArea = packLevel.GetDimensions().get("length") * packLevel.GetDimensions().get("width");
            int boxArea = biggestBox.GetDimension().get("length") * biggestBox.GetDimension().get("width");

            if (packLevelArea - boxArea > 0) {

                if (packLevel.GetDimensions().get("length")
                        - biggestBox.GetDimension().get("length") > 0) {

                    FillSpace(
                            new Container(packLevel.GetDimensions().get("length")
                                    - biggestBox.GetDimension().get("length"),
                                    biggestBox.GetDimension().get("width"),
                                    biggestBox.GetDimension().get("height")),
                            level
                    );
                }
                if (packLevel.GetDimensions().get("width")
                        - biggestBox.GetDimension().get("width") > 0) {

                    FillSpace(
                            new Container(packLevel.GetDimensions().get("length"),
                                    packLevel.GetDimensions().get("width")
                                            - biggestBox.GetDimension().get("width"),
                                    biggestBox.GetDimension().get("height")),
                            level
                    );
                }
                if (!boxes.isEmpty()) PackLevel();
            } else {
                PackLevel();
            }
        }



    }

    public void FillSpace(Container space, Level level){

        int spaceVolume = space.GetVolume();
        Box selectedBox = null;
        int selectedBoxVolume = 0;

        for(Box box : boxes){
            if (box.GetVolume() > spaceVolume) continue;
            else{
                if(FitBox(space, box)){
                    if(box.GetVolume() > selectedBoxVolume){
                        selectedBox = box;
                        selectedBoxVolume = box.GetVolume();
                    }
                }
            }
        }
        if(selectedBox != null){
            level.Add(selectedBox);
            boxes.remove(selectedBox);

            if(space.GetDimensions().get("length")
                    -selectedBox.GetDimension().get("length") > 0){

                FillSpace(
                        new Container(space.GetDimensions().get("length")
                                -selectedBox.GetDimension().get("length"),
                                selectedBox.GetDimension().get("width"),
                                space.GetDimensions().get("height")),
                        level
                );
            }
            if(space.GetDimensions().get("width")
                    -selectedBox.GetDimension().get("width") > 0){

                FillSpace(
                        new Container(space.GetDimensions().get("length"),
                             space.GetDimensions().get("width")
                                -selectedBox.GetDimension().get("width"),
                                space.GetDimensions().get("height")),
                        level
                );
            }
        }

    }




}

