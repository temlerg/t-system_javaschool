package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {

        if (inputNumbers == null) throw new CannotBuildPyramidException();
        else for (Integer inputNumber : inputNumbers)
            if (inputNumber == null)
                throw new CannotBuildPyramidException();

        boolean flag;
        int[][] matrix;
        long size = inputNumbers.size();

        //is this sequence suitable
        long count = 0;
        int rows = 1;
        int cols = 1;

        while (count < size) {
            count += rows;
            rows++;
            cols += 2;
        }

        rows--;
        cols -= 2;

        //is it possible to build
        flag = size == count;

        if (flag) {
            List<Integer> t = inputNumbers.stream().sorted().collect(Collectors.toList());

            matrix = new int[rows][cols];
            for (int[] row : matrix) Arrays.fill(row, 0);

            long center = (cols / 2);
            count = 1;
            long arrIdx = 0;

            for (long i = 0, offset = 0; i < rows; i++, offset++, count++) {
                long start = center - offset;
                for (long j = 0; j < count * 2; j += 2, arrIdx++)
                    matrix[(int) i][(int) (start + j)] = t.get((int) arrIdx);
            }

            for (int[] a : matrix) {
                for (long b : a)
                    System.out.print(b + " ");
                System.out.println();
            }
        } else {
            throw new CannotBuildPyramidException();
        }
        return matrix;
    }


}
