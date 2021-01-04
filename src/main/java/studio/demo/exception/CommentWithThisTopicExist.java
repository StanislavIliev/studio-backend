package studio.demo.exception;

public class CommentWithThisTopicExist extends Exception {
    public CommentWithThisTopicExist (String msg){
        super(msg);
    }
}
