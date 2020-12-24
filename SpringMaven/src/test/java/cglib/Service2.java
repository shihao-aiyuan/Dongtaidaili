package cglib;

/**
 * @author shihao
 * @create 2020-09-02 14:07
 */
public class Service2 {

    public void m1(){
        System.out.println("我是m1方法");
        this.m2();
    }

    public void m2(){
        System.out.println("我是m2方法");
    }
}
