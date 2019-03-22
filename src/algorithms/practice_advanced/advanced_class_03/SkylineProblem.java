package algorithms.practice_advanced.advanced_class_03;

import java.util.*;

public class SkylineProblem {

    public class Border implements Comparable {
        public int index;
        public int height;
        public boolean isLeft;

        public Border(int index, int height, boolean isLeft) {
            this.index = index;
            this.height = height;
            this.isLeft = isLeft;
        }


        public int compareTo(Object object) {
            Border border = (Border) object;
            if (this.index != border.index) {
                return this.index - border.index;
            }
            if (this.isLeft != border.isLeft) {
                return this.isLeft ? -1 : 1;
            }
            return 0;
        }
    }


    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List buildingOutline(int[][] buildings) {
        //1、split one building to two borders and sort by border's index
        Border[] borders = new Border[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            int[] oneBuilding = buildings[i];
            borders[i * 2] = new Border(oneBuilding[0], oneBuilding[2], true);
            borders[i * 2 + 1] = new Border(oneBuilding[1], oneBuilding[2], false);
        }
        Arrays.sort(borders);

        //2、traversal borders and record the max height of each index

        //key->height   value->the count of the height
        TreeMap<Integer, Integer> countOfH = new TreeMap<>();
        //key->index    value->the max height of the index
        TreeMap<Integer, Integer> maxHOfPos = new TreeMap<>();
        for (int i = 0; i < borders.length; i++) {
            int height = borders[i].height;
            if (!countOfH.containsKey(height)) {
                countOfH.put(height, 1);
            }else {
                int count = countOfH.get(height);
                if (borders[i].isLeft) {
                    countOfH.put(height, count + 1);
                } else {
                    countOfH.put(height, count - 1);
                    if (countOfH.get(height) == 0) {
                        countOfH.remove(height);
                    }
                }
            }

            if (countOfH.isEmpty()) {
                maxHOfPos.put(borders[i].index, 0);
            } else {
                //lastKey() return the maxHeight in countOfH RedBlackTree->log(2,N)
                maxHOfPos.put(borders[i].index, countOfH.lastKey());
            }
        }


        //3、draw the buildings outline according to the maxHOfPos
        int start = 0;
        int height = 0;
        List res = new ArrayList();
        for (Map.Entry<Integer, Integer> entry : maxHOfPos.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (height != curMaxHeight) {
                //if the height don't be reset to 0，the curPosition is the end
                if (height != 0) {
                    List record = new ArrayList();
                    record.add(start);
                    record.add(curPosition);//end
                    record.add(height);

                    res.add(record);
                }
                //reset the height and start
                height = curMaxHeight;
                start = curPosition;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] buildings = {
                {1, 3, 3},
                {2, 4, 4},
                {5, 6, 1}
        };
        System.out.println(new SkylineProblem().buildingOutline(buildings));

    }
}
