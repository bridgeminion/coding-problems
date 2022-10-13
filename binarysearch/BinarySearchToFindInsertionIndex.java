package binarysearch;

public class BinarySearchToFindInsertionIndex {
    /**
     * if smaller than smallest, left never changes, always right = mid,
     * if larger than largest, right never changes, always left = mid + 1;
     * @param nums
     * @param target
     * @return
     */
    private int search(int[] nums, int target) {
        int left = 0, right = nums.length, mid = 0;
        while (left < right) {
            mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        BinarySearchToFindInsertionIndex app = new BinarySearchToFindInsertionIndex();
        int[] nums = {1,5,88,999};
        System.out.println(app.search(nums, 0)); // 0
        System.out.println(app.search(nums, 90));  // 3
        System.out.println(app.search(nums, 9999));  // 4
    }

}
