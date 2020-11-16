package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }

        if (x.size() > y.size()) {
            return false;
        }

        int[] countAr = new int[x.size()];

        for (int i = 0; i < x.size(); ++i) {
            for (int j = 0; j < y.size(); ++j) {
                if (y.get(j).equals(x.get(i))) {
                    countAr[i] = j;
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < x.size() - 1; ++i) {
            if (countAr[i] > countAr[i + 1]){
                flag = false;
            }
        }

        return flag;
    }
}
