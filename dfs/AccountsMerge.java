package dfs;

import java.util.*;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> name = new HashMap<>();
        for (List<String> account : accounts) {
            String n = account.get(0);
            for (int i=1; i<account.size(); i++) {
                name.put(account.get(i), n);
                if (i == 1) continue;
                String n1 = account.get(i);
                String n2 = account.get(i-1);
                graph.putIfAbsent(n1, new HashSet<>());
                graph.putIfAbsent(n2, new HashSet<>());
                graph.get(n1).add(n2);
                graph.get(n2).add(n1);
            }
        }
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : name.keySet()) {
            if (visited.add(email)) {
                List<String> list = new ArrayList<>();
                dfs(graph, email, list, visited);
                Collections.sort(list);
                list.add(0, name.get(email));
                result.add(list);
            }
        }

        return result;
    }

    private void bfs(Map<String, Set<String>> map, String email, List<String> list, Set<String> visited) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(email);
        visited.add(email);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            list.add(s);
            Set<String> sibs = map.get(s);
            if (sibs != null) {
                for (String sib : sibs) {
                    if (visited.add(sib)) {
                        queue.offer(sib);
                    }
                }
            }
        }
    }

    private void dfs(Map<String, Set<String>> map, String email, List<String> list, Set<String> visited) {
        list.add(email);
        if (map.containsKey(email)) {
            for (String s : map.get(email)) {
                if (visited.add(s)) {
                    dfs(map, s, list, visited);
                }
            }
        }
    }

}
