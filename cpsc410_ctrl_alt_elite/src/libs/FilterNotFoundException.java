package libs;

public class FilterNotFoundException extends RuntimeException {
    public FilterNotFoundException(String s){
        System.out.println("Filter not found. "+s);
    }
}
