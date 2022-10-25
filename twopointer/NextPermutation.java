package twopointer;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int m = nums.length;
        int i = m-2;
        while (i >= 0) {
            if (nums[i] < nums[i+1]) {
                break;
            }
            i--;
        }
        if (i != -1) {
            for (int j=m-1; j>i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        reverse(nums, i+1, m-1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    private void swap (int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        NextPermutation app = new NextPermutation();
        int[] nums = {3,2,1};
        app.nextPermutation(nums);
    }
}
