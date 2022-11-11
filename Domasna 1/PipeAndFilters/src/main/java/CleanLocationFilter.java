import java.text.ParseException;

public class CleanLocationFilter implements Filter<String>{
    @Override
    public String execute(String input) throws ParseException {
        String[] fields = input.split(",");
        String location = fields[3];
        fields[3] = location.replace(" • "," ");
        String res = "";
        for (String field : fields){
            res += field + ",";
        }
        return res.substring(0, res.length()-1);
    }
}
