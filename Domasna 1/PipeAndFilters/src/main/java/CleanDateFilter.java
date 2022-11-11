import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CleanDateFilter implements Filter<String> {

    @Override
    public String execute(String input) throws ParseException {
        String[] fields = input.split(",");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm a");
        LocalDateTime now = LocalDateTime.now();
        String strDate = fields[1];

        if (strDate.contains("Date")) {
            fields[1] = strDate;
        }
        else if (strDate.isEmpty() || strDate.contains("Tomorrow") || strDate.contains("Today")) {
            fields[1] = "No Date";
        } else if (strDate.contains("+")) {
            int index = strDate.indexOf("+") - 1;
            strDate = strDate.substring(0, index);
            Date date = simpleDateFormat.parse(strDate);
            date.setYear(now.getYear());
            fields[1] = date.toString().replace("3922","2022");
        } else {
            Date date = simpleDateFormat.parse(strDate);
            date.setYear(now.getYear());
            fields[1] = date.toString().replace("3922","2022");
        }
        String res = "";
        for (String field : fields) {
            res += field + ",";
        }
        return res.substring(0, res.length() - 1);
    }
}
