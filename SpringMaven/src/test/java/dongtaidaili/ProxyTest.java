package dongtaidaili;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shihao
 * @create 2020-09-01 17:46
 */
public class ProxyTest {

    @Test
    public void m1(){
        Iservice serviceA=new ServiceA();
        Iservice serviceB = new ServiceB();

        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();

    }

    @Test
    public void m2(){
        Iservice serviceA=new ServiceProxy(new ServiceA());
        Iservice serviceB = new ServiceProxy(new ServiceB());

        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();

    }
    /**
     * jdk代理
     * 1.调用Proxy.getProxyClass方法获取代理类的Class对象
     * 2.使用InvocationHandler接口创建代理类的处理器
     * 3.通过代理类和InvocationHandler创建代理对象
     * 4.上面已经创建好代理对象了，接着我们就可以使用代理对象了
     */
    @Test
    public void m3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,InstantiationException {
        //获取接口的代理类
        Class<Iservice> proxyClass = (Class<Iservice>) Proxy.getProxyClass(Iservice.class.getClassLoader(), Iservice.class);
        //创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler,被调用的方法"+ method.getName());
                return null;
            }
        };

        //创建代理实例
        Iservice proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);

        proxyService.m3();
        proxyService.m2();
        proxyService.m1();

    }

    /**
     * jdk代理
     * 1.使用InvocationHandler接口创建代理类的处理器
     * 2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
     * 3.使用代理对象
     */
    @Test
    public void m4(){
        //创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler,被调用的方法"+ method.getName());
                return null;
            }
        };
        //创建代理实例
        Iservice proxyService  =(Iservice) Proxy.newProxyInstance(Iservice.class.getClassLoader(), new Class[]{Iservice.class}, invocationHandler);

        proxyService.m3();
        proxyService.m2();
        proxyService.m1();
    }


    @Test
    public void costTimeProxy(){
        Iservice serviceA = CosTimeInvocationHandler.createProxy(new ServiceA(), Iservice.class);
        Iservice serviceB = CosTimeInvocationHandler.createProxy(new ServiceB(), Iservice.class);

        serviceA.m1();
        serviceA.m2();
        serviceA.m3();

        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    @Test
    public void userService(){
        IUserService userService = CosTimeInvocationHandler.createProxy(new UserService(), IUserService.class);
        userService.insert("浩哥");
    }
}
