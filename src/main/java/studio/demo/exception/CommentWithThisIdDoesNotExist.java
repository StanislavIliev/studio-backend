package studio.demo.exception;

public class CommentWithThisIdDoesNotExist extends Throwable {
    public CommentWithThisIdDoesNotExist(String msg){
        super(msg);
    }
}
