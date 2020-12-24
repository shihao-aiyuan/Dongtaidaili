package excception;

/**
 * @author shihao
 * @create 2020-11-02 17:28
 */
public class MyException extends RuntimeException {


    private  int code;


    public MyException() {
        super();
    }


    public MyException(int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
