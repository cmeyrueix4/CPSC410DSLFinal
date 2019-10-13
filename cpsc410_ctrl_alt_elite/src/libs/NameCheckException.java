package libs;

public class NameCheckException extends RuntimeException {
    public NameCheckException(String s){
        System.err.println("NAME CHECK FAIL!!! This image was not loaded "+s);
    }
}
