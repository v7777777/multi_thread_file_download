package parser;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Task implements Callable<DownloadResult> {

    String downloadFolder;
    ConcurrentLinkedQueue <String> pictureLinks;
    DownloadResult downloadResult;

    public Task (String downloadFolder, ConcurrentLinkedQueue <String> pictureLinks, DownloadResult downloadResult){
       this.downloadFolder=downloadFolder;
       this.pictureLinks=pictureLinks;
       this.downloadResult = downloadResult;

    }

    @Override
    public DownloadResult call()  {

       // System.out.println(Thread.currentThread().getName());

        if (!Files.exists(Paths.get(downloadFolder))) {Paths.get(downloadFolder).toFile().mkdir();}

        String currentDownload = pictureLinks.poll();

        try {

            URL url = new URL(currentDownload);
            InputStream inputStream = url.openStream();
            Path destPath = Paths.get(downloadFolder + File.separator + currentDownload.substring(currentDownload.lastIndexOf("/") + 1));
            Files.copy(inputStream, destPath, StandardCopyOption.REPLACE_EXISTING);
            downloadResult.getDownloadedLinks().add(destPath.toString());
        }
        catch (Exception e ) {
            e.printStackTrace();
            String error = e.getMessage();
            downloadResult.getDownloadErrors().add(error); }

        return downloadResult;
    }
}
