package studio.demo.exception;

public class ProductAlreadyExist extends Exception {
    public ProductAlreadyExist (String msg){
        super(msg);
    }

}
