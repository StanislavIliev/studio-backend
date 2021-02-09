package studio.demo.exception;

public class ProcedureAlreadyExist extends Throwable {
    public ProcedureAlreadyExist (String msg){
        super(msg);
    }

}
