package studio.demo.exception;

public class ProductDoesNotExist extends Throwable {
    public ProductDoesNotExist(String msg){
        super(msg);
    }

}
