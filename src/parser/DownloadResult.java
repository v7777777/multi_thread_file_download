package parser;

import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DownloadResult {

    ArrayList<String> downloadErrors;
    ArrayList <String> downloadedLinks;

    public DownloadResult (ArrayList<String> downloadErrors, ArrayList <String> downloadedLinks)

    {
        this.downloadErrors=downloadErrors;
        this.downloadedLinks=downloadedLinks;

    }

    public void printDownloadedLinks () {
        downloadedLinks.forEach(System.out::println);
    }

    public void countDownloadedLinks () {
        System.out.println(downloadedLinks.size() + " ссылок загружено");
    }

    public  void countDownloadErrors () {
        System.out.println(downloadErrors.size() + " ссылок не удалось загрузить");
    }




}
