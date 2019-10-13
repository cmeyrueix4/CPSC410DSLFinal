package libs;

public class FilterNotFoundException extends RuntimeException {
    public FilterNotFoundException(String s){
        System.err.println("Filter not found. "+s);
    }
}
