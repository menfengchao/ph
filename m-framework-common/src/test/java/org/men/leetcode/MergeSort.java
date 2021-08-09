package org.men.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.Scanner;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/8/4 2:31 下午
 **/
public class MergeSort {

    private static int[] tmp = new int[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = sc.nextInt();
        }
        mergeSort(nums, 0, n - 1);
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d ", nums[i]);
        }
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + right >>> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int i = left, j = mid + 1, k = 0;
        //比较左右两侧数据 因为 nums已经部分有序 所以只要一个不满足则跳出循环 由下面部分弥补
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }
        //将左侧剩余部分copy进nums
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        //将右侧剩余部分copy进nums
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        //从left开始 不能干扰前面的 right结束 将temp部分考入nums
        for (i = left, j = 0; i <= right; ++i, ++j) {
            nums[i] = tmp[j];
        }
    }


}
