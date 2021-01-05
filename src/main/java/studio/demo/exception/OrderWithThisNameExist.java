package studio.demo.exception;

public class OrderWithThisNameExist extends Throwable {
    public OrderWithThisNameExist(String msg){
        super(msg);
    }
}
