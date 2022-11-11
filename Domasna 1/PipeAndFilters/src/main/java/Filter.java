import java.text.ParseException;

public interface Filter<T> {
    T execute(T input) throws ParseException;
}
