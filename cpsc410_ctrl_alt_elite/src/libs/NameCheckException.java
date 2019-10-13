package libs;

public class NameCheckException extends RuntimeException {
    public NameCheckException(String s){
        System.out.println("NAME CHECK FAIL!!! This image was not loaded "+s);
    }
}
