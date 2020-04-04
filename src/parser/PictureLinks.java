package parser;

import org.jsoup.select.Elements;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class PictureLinks  {

    Elements links ;
    Elements notValidLinks;


    public static ConcurrentLinkedQueue<String> queueLinks = new ConcurrentLinkedQueue();

    public PictureLinks (Elements links, Elements notValidLinks)

    {
        this.links=links;
        this.notValidLinks = notValidLinks;

    }


    public ConcurrentLinkedQueue <String> getTextLinks () {

        queueLinks.addAll(links.stream().
            filter(p -> !(notValidLinks.contains(p))).
            map(e -> e.attributes().get("src")).
            collect(Collectors.toList()));
        return queueLinks;
}






}
