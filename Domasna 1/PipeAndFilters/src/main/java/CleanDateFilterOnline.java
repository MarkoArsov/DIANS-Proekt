import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CleanDateFilterOnline implements Filter<String> {
    @Override
    public String execute(String input) throws ParseException {
        String[] fields = input.split(",");
        String strDate = fields[1];
        if (strDate.contains("Date")){
            fields[1] = strDate;
        }
        else if (strDate.isEmpty()){
            fields[1] = "No Date";
        }
        else {
            int index = strDate.indexOf("(")-1;
            strDate = strDate.substring(0,index);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d yyyy HH:mm a z");
            Date date = simpleDateFormat.parse(strDate);
            fields[1] = date.toString();
        }
        String res = "";
        for (String field : fields){
            res += field + ",";
        }
        return res.substring(0, res.length()-1);
    }
}
