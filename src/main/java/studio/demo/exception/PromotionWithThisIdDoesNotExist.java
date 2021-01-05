package studio.demo.exception;

public class PromotionWithThisIdDoesNotExist extends Throwable {
    public PromotionWithThisIdDoesNotExist(String msg){
        super(msg);
    }
}
