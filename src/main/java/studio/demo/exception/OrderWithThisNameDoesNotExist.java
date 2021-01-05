package studio.demo.exception;

public class OrderWithThisNameDoesNotExist extends Exception {
   public OrderWithThisNameDoesNotExist (String msg){
        super(msg);
    }
}
