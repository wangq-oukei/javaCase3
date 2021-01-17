package com.wangq.case3.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author songdan
 * @Created 2021-01-16 17:18
 *
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] sum = twoSum(nums, target);
        int[] sum2 = twoSum(nums, target);
        System.out.println(Arrays.toString(sum));
        System.out.println(Arrays.toString(sum2));

    }

    /**
     *时间复杂度：O(N^2)，其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     *
     * 空间复杂度：O(1)。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
                if (hash.containsKey(target - nums[i])) {
                    return new int[]{i, hash.get(target - nums[i])};
                }
                hash.put(nums[i], i);
        }

        return null;
    }
}
