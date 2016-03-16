package sorts.logic;

import java.util.ArrayList;

/**
 * @author : First created by Evgeniy Kim with code by Evgeniy Kim
 * @date : 19/02/16, last edited by Evgeniy Kim on 19/02/16
 */
public class SelectionSort {
    /**
     * Selection sort
     * Partitions list into sorted and unsorted halves, taking elements from the unsorted half and
     * placing thme in the sorted, in it's correct position.
     *
     * @param state ArrayList which contains a randomly ordered set of numbers
     * @return
     */
    public static ArrayList<SortableComponent> sort(ArrayList<Integer> state) {
        int size = state.size();
        ArrayList<SortableComponent> allStates = new ArrayList<>();

        int i, j;
        for (j = 0; j < size - 1; j++) {
            int min = j;
            for (i = j + 1; i < size; i++) {
                SortableComponent s = new SortableComponent(getByValue(state), min, i, false);
                allStates.add(s);
                if (state.get(i) < state.get(min)) {
                    min = i;
                    SortableComponent c = new SortableComponent(getByValue(state), j, i, true);
                    allStates.add(c);
                }
            }
            if (min != j) { //swapping
                Integer temp = state.get(j);
                state.set(j, state.get(min));
                state.set(min, temp);
                SortableComponent s = new SortableComponent(getByValue(state), min, j, true);
                allStates.add(s);
            }
        }
        return allStates;
    }

    /**
     * Pass by value
     *
     * @param list
     * @return
     */
    public static ArrayList<Integer> getByValue(ArrayList<Integer> list) {
        return new ArrayList<>(list);
    }
}