package studio.demo.exception;

public class ProcedureDoesNotExist extends Throwable {
    public ProcedureDoesNotExist (String msg){
        super(msg);
    }
}
