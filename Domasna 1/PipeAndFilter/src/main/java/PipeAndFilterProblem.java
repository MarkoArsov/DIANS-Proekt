import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PipeAndFilterProblem {

    public static void main(String[] args) throws IOException, ParseException {

        File scrapedEvents = new File("resources\\events_scraped.csv");
        Scanner scanner = new Scanner(scrapedEvents);
        scanner.useDelimiter(",");

        Pipe<String> pipe = new Pipe<>();

        RemoveIndexFilter removeIndexFilter = new RemoveIndexFilter();
        pipe.addFilter(removeIndexFilter);

        CleanDateFilter dateFilter = new CleanDateFilter();
        pipe.addFilter(dateFilter);

        File eventsFinal = new File("output\\events.csv");
        if (!eventsFinal.exists()){
            eventsFinal.createNewFile();
        }
        FileWriter writer = new FileWriter(eventsFinal);

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = pipe.runFilters(line);
            writer.write(line+"\n");
            writer.flush();
        }
        writer.close();


    }
}
