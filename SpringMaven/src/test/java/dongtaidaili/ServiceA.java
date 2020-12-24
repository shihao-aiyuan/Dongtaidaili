package dongtaidaili;

/**
 * @author shihao
 * @create 2020-09-01 17:43
 */
public class ServiceA implements Iservice {
    @Override
    public void m1() {
        System.out.println("我是ServiceA中的方法m1    ");
    }

    @Override
    public void m2() {
        System.out.println("我是ServiceA中的方法m2-----");
    }

    @Override
    public void m3() {
        System.out.println("我是ServiceA中的方法m3++++");
    }
}
