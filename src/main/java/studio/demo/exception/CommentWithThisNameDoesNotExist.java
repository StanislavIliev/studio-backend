package studio.demo.exception;

public class CommentWithThisNameDoesNotExist extends Exception {
    public CommentWithThisNameDoesNotExist (String msg){
        super(msg);
    }
}

