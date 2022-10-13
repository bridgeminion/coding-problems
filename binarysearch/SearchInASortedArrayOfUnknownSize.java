package binarysearch;

import common.ArrayReader;

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
