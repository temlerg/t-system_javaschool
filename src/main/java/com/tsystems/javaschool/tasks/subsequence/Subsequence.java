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
        // TODO: Implement the logic here
        if(x == null || y == null) throw new IllegalArgumentException();
        if (x.size() > y.size()) return false;
        int n[] = new int[y.size()];
        for (int i = 0; i < y.size(); ++i) n[i] = -1;

        for (int i = 0; i < x.size(); ++i)
            for (int j = 0; j < y.size(); ++j)
                if (y.get(j) == x.get(i)) n[j] = i;

        int t = -1;
        for (int i = 0; i < y.size(); ++i) {
            if (n[i] != -1)
                if (n[i] >= t) t = n[i];
                else return false;
        }
        return t >= x.size()-1;
    }
}
