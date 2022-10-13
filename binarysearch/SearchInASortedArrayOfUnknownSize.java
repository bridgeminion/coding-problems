package binarysearch;

import common.ArrayReader;

/**
 * This is an interactive problem.
 *
 * You have a sorted array of unique elements and an unknown size. You do not have an access to the array
 * but you can use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 *
 * returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * returns 231 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 *
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in secret and its index is 4.
 * Example 2:
 *
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in secret so return -1.
 */
public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int high = 1;
        while (reader.get(high) <= target) {  // find upper bound index which is open, therefore need to add =
            high *= 2;
        }
        int left = high/2, right = high, mid = 0;
        while (left < right) {
            mid = left + (right - left)/2;
            int value = reader.get(mid);
            if (value == target) {
                return mid;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }
}
