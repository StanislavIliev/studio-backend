package studio.demo.exception;

public class PromotionWithThisNameDoesNotExist extends Throwable {
    public PromotionWithThisNameDoesNotExist(String msg){
        super(msg);
    }
}
