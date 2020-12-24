package cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shihao
 * @create 2020-09-02 18:21
 */
public class CostTimeProxy implements MethodInterceptor {

    private Object target;

    public  CostTimeProxy(Object target){
        this.target=target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        //调用被代理对象（即target）的方法，获取结果
        Object result = method.invoke(target, objects); //@1
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒)：" + (endTime - starTime));
        return result;
    }

    public static <T> T createProxy(T target){
        CostTimeProxy costTimeProxy=new CostTimeProxy(target);
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());

        return (T)enhancer.create();
    }
}
