import java.util.Arrays;

public class RemoveIndexFilter implements Filter<String>{

    @Override
    public String execute(String input) {
        String[] fields = input.split(",");
        String[] noIndex = Arrays.copyOfRange(fields, 1, fields.length);
        String res = "";
        for (String field : noIndex){
            res += field + ",";
        }
        return res;
    }

}
