package dongtaidaili;

import javax.jnlp.IntegrationService;

/**
 * @author shihao
 * @create 2020-09-01 17:50
 */
public class ServiceProxy implements Iservice {

    private Iservice iservice;  //目标对象  被代理的对象

    public ServiceProxy(Iservice iservice){
        this.iservice=iservice;
    }


    @Override
    public void m1() {
        long startTime = System.nanoTime();
        this.iservice.m1();
        long endTime = System.nanoTime();

        System.out.println(this.iservice.getClass()+" .m1()方法耗时(纳秒)"+ (endTime-startTime));
    }

    @Override
    public void m2() {
        long startTime = System.nanoTime();
        this.iservice.m2();
        long endTime = System.nanoTime();

        System.out.println(this.iservice.getClass()+" .m2()方法耗时(纳秒)"+ (endTime-startTime));
    }

    @Override
    public void m3() {
        long startTime = System.nanoTime();
        this.iservice.m3();
        long endTime = System.nanoTime();

        System.out.println(this.iservice.getClass()+" .m3()方法耗时(纳秒)"+ (endTime-startTime));
    }
}
