import parser.*;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

            String page = "https://lenta.ru/";
            String downloadFolder = "C:\\Users\\v.lesina\\Downloads\\pics";

            ConcurrentLinkedQueue<String> picsLinks = HtmlParser.parse(page).getTextLinks();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

            Thread.sleep(4000);

            DownloadResult downloadResult = new DownloadResult();

            for (int i =0; i < (picsLinks.size() - 1); i ++)  {

            executor.submit(new Task(downloadFolder, picsLinks, downloadResult));  }

            downloadResult = executor.submit(new Task(downloadFolder, picsLinks, downloadResult)).get();

            executor.shutdown();

            downloadResult.printDownloadedLinks();
            downloadResult.countDownloadErrors();
            downloadResult.countDownloadedLinks();

            System.out.println(System.currentTimeMillis()-start);


}


}
