import java.io.*;
import java.text.ParseException;
import java.util.*;

public class PipeAndFilterProblem {

    public static void processOnlineEvents() throws IOException, ParseException {
        File scrapedEvents = new File("resources\\online_events_scraped.csv");
        Scanner scanner = new Scanner(scrapedEvents);
        scanner.useDelimiter(",");

        Pipe<String> pipe = new Pipe<>();

        RemoveIndexFilter removeIndexFilter = new RemoveIndexFilter();
        pipe.addFilter(removeIndexFilter);

        CleanDateFilterOnline dateFilter = new CleanDateFilterOnline();
        pipe.addFilter(dateFilter);

        File eventsFinal = new File("output\\online_events.csv");
        if (!eventsFinal.exists()) {
            eventsFinal.createNewFile();
        }
        FileWriter writer = new FileWriter(eventsFinal);

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = pipe.runFilters(line);
            writer.write(line + "\n");
            writer.flush();
        }
        writer.close();
    }

    public static void processEvents() throws IOException, ParseException {

        File scrapedEvents = new File("resources\\events_scraped.csv");

        BufferedReader br = new BufferedReader(new FileReader(scrapedEvents));

        Pipe<String> pipe = new Pipe<>();

        RemoveIndexFilter removeIndexFilter = new RemoveIndexFilter();
        pipe.addFilter(removeIndexFilter);

        CleanDateFilter dateFilter = new CleanDateFilter();
        pipe.addFilter(dateFilter);

        CleanLocationFilter locationFilter = new CleanLocationFilter();
        pipe.addFilter(locationFilter);

        File eventsFin = new File("output\\events.csv");
        if (!eventsFin.exists()) {
            eventsFin.createNewFile();
        }
        FileWriter writer = new FileWriter(eventsFin);

        String line = "";

        line = br.readLine();
        while (line!=null) {
            line = pipe.runFilters(line);
            writer.write(line+"\n");
            writer.flush();

            line = br.readLine();
          }
        writer.close();
    }

    public static void main(String[] args) throws IOException, ParseException {

        processOnlineEvents();
        processEvents();

    }
}
