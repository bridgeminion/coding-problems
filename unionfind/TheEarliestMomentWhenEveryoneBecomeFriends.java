package unionfind;

import java.util.Arrays;

public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public int earliestAcq(int[][] logs, int n) {
        UnionFind uf = new UnionFind(n);
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        for (int i=0; i<logs.length; i++) {
            int[] triple = logs[i];
            uf.union(triple[1], triple[2]);
            if (uf.getSize() == 1) {
                return triple[0];
            }
        }

        return -1;
    }

    class UnionFind {
        int[] parents;
        int size;
        public UnionFind(int n) {
            size = n;
            parents = new int[n];
            for (int i=0; i<n; i++) {
                parents[i] = i;
            }
        }

        public void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if (parentA != parentB) {
                parents[parentA] = parentB;
                size--;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }
            return parents[a];
        }

        public int getSize() {
            return size;
        }
    }

    public static void main(String[] args) {
        TheEarliestMomentWhenEveryoneBecomeFriends app = new TheEarliestMomentWhenEveryoneBecomeFriends();
        int[][] logs = {{0,2,0},{7,4,3},{2,2,1},{1,0,1},{5,4,1},{6,3,2},{8,3,1},{3,0,4}};
        System.out.println(app.earliestAcq(logs, 5));
    }

}
