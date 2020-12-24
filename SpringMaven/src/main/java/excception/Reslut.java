package excception;

/**
 * @author shihao
 * @create 2020-11-02 17:31
 */
public class Reslut {

    private int code;

    private  String Message;

    public Reslut(int code, String message) {
        this.code = code;
        Message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
