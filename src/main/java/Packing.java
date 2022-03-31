import Model.*;
import ch.qos.logback.classic.Logger;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;

public class Packing {

    private @Getter ArrayList<Box> boxes = new ArrayList<Box>();
    private Container container = new Container();
    private @Getter ArrayList<Level> levels = new ArrayList<Level>();

    Logger logger = (Logger) LoggerFactory.getLogger(Packing.class);

    public Packing(ArrayList<Box> boxes, Container container) {
        this.boxes = boxes;
        this.container = container;
    }

    public Packing(Box box, Container container) {
        this.boxes.add(box);
        this.container = container;
    }



    public double GetPercentageOfUnusedContainer(){
        double percent=1;
        double sumOfBoxVolumes = 0;
        double containerVolume = container.getVolume();

        for (Level level : levels) {
            for (Box box : level.getPackedBoxes()) {
                sumOfBoxVolumes += box.getVolume();
            }

        }

        return percent;
    }

    public boolean TryToFit(@NotNull Container freeSpace, @NotNull Box box) {
        if (freeSpace.getDimensions().get("length") >= box.getDimension().get("length")
                && freeSpace.getDimensions().get("width") >= box.getDimension().get("width")
                && freeSpace.getDimensions().get("height") >= box.getDimension().get("height")
        ){
            return true;}

        return false;
    }

    public boolean FitBox(Container tempSpace, Box box) {
        if (TryToFit(tempSpace, box)){
            logger.info("Box {} fitted", box);
            return true;
        }
        else {
            for (int i = 0; i < 3; i++) {
                if (TryToFit(tempSpace, box.RotateBox())) {
                    logger.info("Box {} fitted", box);
                    return true;
                }
            }
        }
        logger.info("Box {} does not fit", box);
        return false;
    }

    public int CountOfBoxesFit() {
            int count = 0;

            for (Level level : levels) {
                for (Box box : level.getPackedBoxes()) {
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
                if (level != null && !level.getPackedBoxes().isEmpty()) {
                    maxBoxHeight = level.getPackedBoxes().get(0).getDimension().get("height");
                    for (Box box : level.getPackedBoxes()) {
                        if (box.getDimension().get("height") >= maxBoxHeight)
                            maxBoxHeight = box.getDimension().get("height");
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

        if (HeightOfPackedStack() >= container.getDimensions().get("height")) return;

        Container packLevel = new Container(container.getDimensions().get("length"),
                container.getDimensions().get("width"),
                container.getDimensions().get("height") - HeightOfPackedStack());


        for (Box box : boxes) {

            int surfaceArea = box.getDimension().get("length") * box.getDimension().get("width");

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
                    new Container(packLevel.getDimensions().get("length"),
                            packLevel.getDimensions().get("width"),
                            packLevel.getDimensions().get("height")),
                    level
            );
        } else {
            int packLevelArea = packLevel.getDimensions().get("length") * packLevel.getDimensions().get("width");
            int boxArea = biggestBox.getDimension().get("length") * biggestBox.getDimension().get("width");

            if (packLevelArea - boxArea > 0) {

                if (packLevel.getDimensions().get("length")
                        - biggestBox.getDimension().get("length") > 0) {

                    FillSpace(
                            new Container(packLevel.getDimensions().get("length")
                                    - biggestBox.getDimension().get("length"),
                                    biggestBox.getDimension().get("width"),
                                    biggestBox.getDimension().get("height")),
                            level
                    );
                }
                if (packLevel.getDimensions().get("width")
                        - biggestBox.getDimension().get("width") > 0) {

                    FillSpace(
                            new Container(packLevel.getDimensions().get("length"),
                                    packLevel.getDimensions().get("width")
                                            - biggestBox.getDimension().get("width"),
                                    biggestBox.getDimension().get("height")),
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

        int spaceVolume = space.getVolume();
        Box selectedBox = null;
        int selectedBoxVolume = 0;

        for(Box box : boxes){
            if (box.getVolume() > spaceVolume) continue;
            else{
                if(FitBox(space, box)){
                    if(box.getVolume() > selectedBoxVolume){
                        selectedBox = box;
                        selectedBoxVolume = box.getVolume();
                    }
                }
            }
        }
        if(selectedBox != null){
            level.Add(selectedBox);
            boxes.remove(selectedBox);

            if(space.getDimensions().get("length")
                    -selectedBox.getDimension().get("length") > 0){

                FillSpace(
                        new Container(space.getDimensions().get("length")
                                -selectedBox.getDimension().get("length"),
                                selectedBox.getDimension().get("width"),
                                space.getDimensions().get("height")),
                        level
                );
            }
            if(space.getDimensions().get("width")
                    -selectedBox.getDimension().get("width") > 0){

                FillSpace(
                        new Container(space.getDimensions().get("length"),
                             space.getDimensions().get("width")
                                -selectedBox.getDimension().get("width"),
                                space.getDimensions().get("height")),
                        level
                );
            }
        }

    }




}

