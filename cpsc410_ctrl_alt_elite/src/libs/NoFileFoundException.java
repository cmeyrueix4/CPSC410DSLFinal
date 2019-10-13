package libs;

public class NoFileFoundException extends RuntimeException {
    public NoFileFoundException(String s){
        System.err.println("This image file " + s + " is not found in directory");
    }
}
