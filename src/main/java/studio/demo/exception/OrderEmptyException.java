package studio.demo.exception;

public class OrderEmptyException extends Throwable {
    public OrderEmptyException(String msg){
        super(msg);
    }

}
