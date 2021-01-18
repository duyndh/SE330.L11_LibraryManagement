package utils.DB;

public class TransformException extends Exception {

    public String msg = "";

    public TransformException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
