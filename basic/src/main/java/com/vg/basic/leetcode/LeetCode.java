package com.vg.basic.leetcode;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: xieweij
 * @time: 2021/8/25 15:23
 */
public class LeetCode {

    /**
    不用加减乘除做加法 1,2
     */
    int Add(int num1, int num2) {

        int sum = num1 ^ num2;
        int carry = (num1 & num2) << 1;
        if (carry != 0){
           sum = Add(sum, carry);
        }
        return sum;
    }

    /**
    找出数组中重复的数字。
    在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    输入：[2, 3, 1, 0, 2, 5, 3] 输出：2 或 3 限制：2 <= n <= 100000
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums){
            if (!set.add(i)) return i;
        }
        return -1;
    }

    /**
    给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。

    输入：nums = [2,7,11,15], target = 9 输出：[0,1] 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /** 3. 无重复字符的最长子串 --- 解题思路，滑动窗口
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例 1: 输入: s = "abcabcbb" 输出: 3  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2: 输入: s = "bbbbb"    输出: 1  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3: 输入: s = "pwwkew"   输出: 3  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4: 输入: s = ""         输出: 0
     * 提示：
     *     0 <= s.length <= 5 * 104
     *     s 由英文字母、数字、符号和空格组成
     */
    public int lengthOfLongestSubstring(String s) {
        //------- 个人实现 时间复杂度O（n^2）--------
/*        int maxLength = 0;
        if (s != null && !s.equals("")){
            HashSet<Character> set;
            int slength = s.length();
            for(int i = 0; i < slength; i++){
                if (i > slength/2 && maxLength > i) break;
                set = new HashSet<>();
                for(int j = i; j < slength; j++){
                    if (!set.add(s.charAt(j))) break;
                }
                maxLength = set.size() > maxLength? set.size() : maxLength;
            }
        }
        return maxLength;*/

        //----------滑动窗口法 时间复杂度 O（n）-----------

        HashSet<Character> set = new HashSet<>();

        //end：右指针，初始化为-1
        //max：不重复子字符串最大长度
        int end = 0, max = 0;
        int n = s.length();

        //i相当左指针
        for(int i = 0; i < n; i++){
            //每次都把左边界的元素剔除，当i=0时，set中还没添加元素
            if(i != 0){
                set.remove(s.charAt(i -1));
            }
            //移动右指针，添加进set中，直至遇到重复元素
            while(end < n && set.add(s.charAt(end))){
                end++;
            }
            max = Math.max(max, end- i);
        }
        return max;
    }

    /** 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 示例 1：输入：nums1 = [1,3], nums2 = [2]    输出：2.00000   解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：输入：nums1 = [1,2], nums2 = [3,4]  输出：2.50000   解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 示例 3：输入：nums1 = [0,0], nums2 = [0,0]  输出：0.00000
     * 示例 4：输入：nums1 = [], nums2 = [1]       输出：1.00000
     * 示例 5：输入：nums1 = [2], nums2 = []       输出：2.00000
     *
     * 提示： nums1.length == m
     *       nums2.length == n
     *       0 <= m <= 1000
     *       0 <= n <= 1000
     *       1 <= m + n <= 2000
     *       -10^6 <= nums1[i], nums2[i] <= 10^6
     *
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //初始化指针
        int indexA = 0, indexB = 0;
        int lenA = nums1.length, lenB = nums2.length;

        List<Integer> array = new ArrayList<>();

        while (indexA < lenA || indexB < lenB){
            int n1 = (lenA > 0 && indexA < lenA)? nums1[indexA] : Integer.MAX_VALUE;
            int n2 = (lenB > 0 && indexB < lenB)? nums2[indexB] : Integer.MAX_VALUE;

            if(n1 <= n2){
                array.add(n1);
                indexA++; //移动num1的指针
            }else{
                array.add(n2);
                indexB++;//移动num2的指针
            }
        }

        //判断集合长度奇偶
        if((array.size() & 1) == 1){//奇数
            return array.get(array.size() / 2);
        }else{//偶数
            return (array.get(array.size() / 2) + array.get(array.size() / 2 -1)) / 2d;
        }
    }

    public static void main(String[] args){
        LeetCode leetCode = new LeetCode();
        System.out.println(leetCode.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(leetCode.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(leetCode.findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));
        System.out.println(leetCode.findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println(leetCode.findMedianSortedArrays(new int[]{2}, new int[]{}));
        System.out.println(leetCode.findMedianSortedArrays(new int[]{100000}, new int[]{100001}));
    }
}
