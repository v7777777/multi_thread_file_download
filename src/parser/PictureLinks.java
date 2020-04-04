package parser;

import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PictureLinks {

    public PictureLinks (Elements links, Elements notValidLinks )

    {
        this.links=links;
        this.notValidLinks = notValidLinks;
    }

    Elements links ;
    Elements notValidLinks;


    public List <String> getTextLinks () {
    List <String> textLinks = links.stream().
            filter(p -> !(notValidLinks.contains(p))).
            map(e -> e.attributes().get("src")).
            collect(Collectors.toList());
             return textLinks;
}

    public static DownloadResult downloadPics(List <String> textLinks, String downloadPath)  {

        ArrayList <String> downloadErrors = new ArrayList <>();
        ArrayList <String> downloadedLinks = new ArrayList <>();

        String downloadFolder= downloadPath;

        if (!Files.exists(Paths.get(downloadFolder))) {Paths.get(downloadFolder).toFile().mkdir();}

        for (int i = 0; i < textLinks.size(); i++) {

         try {   URL url = new URL(textLinks.get(i));
            InputStream inputStream = url.openStream();  // НЕ ЗАКРЫЛА???? НУЖНО TRY WITH RESOURCES!!
            Path destPath = Paths.get(downloadFolder + File.separator + textLinks.get(i).substring(textLinks.get(i).lastIndexOf("/") + 1));
            Files.copy(inputStream, destPath, StandardCopyOption.REPLACE_EXISTING);
            downloadedLinks.add(destPath.toString());
         }
         catch (Exception e ) {
             e.printStackTrace();
             String error = e.getMessage();
             downloadErrors.add(error);
             continue;
         }
        }

        DownloadResult downloadResult = new DownloadResult(downloadErrors, downloadedLinks);

        return downloadResult;
    }



}
