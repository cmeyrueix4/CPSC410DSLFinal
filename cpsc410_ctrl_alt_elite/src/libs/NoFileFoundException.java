package libs;

public class NoFileFoundException extends RuntimeException {
    public NoFileFoundException(String s){
        System.out.println("This image file " + s + " is not found in directory");
    }
}
