package studio.demo.exception;

public class PromotionDoesNotExist extends Throwable {
    public PromotionDoesNotExist(String msg){
        super(msg);
    }
}
