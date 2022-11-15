package unionfind;

import java.util.Arrays;

/**
 * There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
 *
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
 *
 * Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 * Example 2:
 *
 * Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
 * Output: 3
 *
 */
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
