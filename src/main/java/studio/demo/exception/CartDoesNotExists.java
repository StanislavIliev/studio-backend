package studio.demo.exception;

public class CartDoesNotExists extends Throwable {
    public CartDoesNotExists(String msg){
        super(msg);
    }

}
