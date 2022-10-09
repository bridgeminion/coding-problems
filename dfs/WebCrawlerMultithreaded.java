package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebCrawlerMultithreaded {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {

        int index = startUrl.indexOf("/", 7);
        String hostName = index == -1 ? startUrl : startUrl.substring(0, index);
        Set<String> result = ConcurrentHashMap.newKeySet();
        Set<String> visited = ConcurrentHashMap.newKeySet();
        dfs(startUrl, htmlParser, result, visited, hostName);

        return new ArrayList(result);
    }

    private void dfs(String url, HtmlParser htmlParser, Set<String> set, Set<String> visited, String hostName) {
        set.add(url);
        List<String> urls = htmlParser.getUrls(url);
        urls.parallelStream().filter(u -> u.startsWith(hostName))
                .filter(u -> visited.add(u)).forEach(uu -> dfs(uu, htmlParser, set, visited, hostName));
    }
}
