package dfs;

import common.NestedInteger;

import java.util.*;

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
