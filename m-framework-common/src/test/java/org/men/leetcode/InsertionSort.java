package org.men.leetcode;

import java.util.Arrays;

/**
 * 插入排序
 * 在插入排序中，经过每一轮的排序处理后，数组前端的数是排好序的
 *
 * @author mfc
 * @version v1.0
 * @date 2021/7/30 1:48 下午
 **/
public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = {1, 2, 9, 7, 5, 8};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1, j, n = nums.length; i < n; i++) {
            int temp = nums[i];
            for (j = i - 1; j > 0 && nums[j] > temp; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }


}
