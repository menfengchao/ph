package org.men.leetcode;

import java.util.Scanner;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/8/10 2:27 下午
 **/
public class QuickSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        quickSort(nums, 0, n - 1);
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d ", nums[i]);
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left - 1;
        int j = right + 1;
        int x = nums[left];
        while (i < j){
            while (nums[++i] < x);
            while (nums[--j] > x);
            if(i < j){
                nums[i] = nums[i]^nums[j];
                nums[j] = nums[i]^nums[j];
                nums[i] = nums[i]^nums[j];
            }
        }
        quickSort(nums, left , j);
        quickSort(nums, j+1, right);
    }
}
