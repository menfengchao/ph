package org.men.leetcode;

import java.util.Arrays;

/**
 * 冒泡排序
 * 在冒泡排序中，经过每一轮的排序处理后，数组后端的数是排好序的
 *
 * @author mfc
 * @version v1.0
 * @date 2021/7/30 10:37 上午
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 7, 13, 1, 9, 11, 0, 88};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 排序主方法
     *
     * @param nums
     */
    private static void bubbleSort(int nums[]) {
        if (nums == null) {
            return;
        }
        //如果为false表示已经是结果了 true 表示还是需要继续冒泡
        Boolean hasChange = true;
        for (int i = 0, n = nums.length; i < n-1 && hasChange; i++) {
            hasChange = false;
            //从小到大 减少外层循环次数 n-i
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChange = true;
                }
            }
        }
    }

    /**
     * 交换
     *
     * @param nums
     * @param j
     * @param i
     */
    private static void swap(int[] nums, int j, int i) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

}
