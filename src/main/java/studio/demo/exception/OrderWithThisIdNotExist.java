package studio.demo.exception;

public class OrderWithThisIdNotExist extends Throwable {
public OrderWithThisIdNotExist(String msg){
    super(msg);
}
}

