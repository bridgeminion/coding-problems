package dfs;

import common.NestedInteger;

import java.util.*;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {
    Deque<NestedInteger> deque;
    public NestedIterator(List<NestedInteger> nestedList) {
        deque = new ArrayDeque(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new RuntimeException();
        }
        return deque.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!deque.isEmpty() && !deque.peekFirst().isInteger()) {
            List<NestedInteger> ni = dfs(deque.pollFirst());
            for (int i=ni.size()-1; i>=0; i--) {
                deque.addFirst(ni.get(i));
            }
        }

        return !deque.isEmpty();
    }

    private List<NestedInteger> dfs(NestedInteger ni) {
        List<NestedInteger> list = new ArrayList<>();
        for (NestedInteger nn : ni.getList()) {
            if (nn.isInteger()) {
                list.add(nn);
            } else {
                list.addAll(dfs(nn));
            }
        }

        return list;
    }
}
