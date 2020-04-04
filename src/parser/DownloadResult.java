package parser;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DownloadResult {

    private Set<String> downloadErrors = ConcurrentHashMap.newKeySet();;

    private Set <String> downloadedLinks = ConcurrentHashMap.newKeySet();;

    public void printDownloadedLinks () {
        downloadedLinks.forEach(System.out::println);
    }

    public void countDownloadedLinks () {
        System.out.println(downloadedLinks.size() + " ссылок загружено");
    }

    public  void countDownloadErrors () {
        System.out.println(downloadErrors.size() + " ссылок не удалось загрузить");
    }

    public Set<String> getDownloadErrors() {
        return downloadErrors;
    }

    public Set<String> getDownloadedLinks() {
        return downloadedLinks;
    }
}
