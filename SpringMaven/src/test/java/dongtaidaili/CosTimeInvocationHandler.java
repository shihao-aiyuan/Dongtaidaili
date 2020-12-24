package dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shihao
 * @create 2020-09-02 10:29
 */
public class CosTimeInvocationHandler implements InvocationHandler {


    private Object targer;

    public CosTimeInvocationHandler(Object targer){
        this.targer=targer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long startTime = System.nanoTime();
        Object invoke = method.invoke(targer, args);
        long endTime = System.nanoTime();
        System.out.println(this.targer.getClass()+".m1()方法耗时时间:"+(endTime-startTime));

        return invoke;
    }


    /**
     * 用来创建tergerInterface接口的代理对象
     *
     * @param targer   需要被代理的对象
     * @param targerInterface   被代理的接口
     * @param <T>
     * @return
     */
    public static <T> T createProxy(Object targer, Class<T> targerInterface){
        if(!targerInterface.isInterface()){
            throw  new IllegalStateException("targerInterface必须是接口类型");
        }else if(!targerInterface.isAssignableFrom(targer.getClass())){
            throw  new IllegalStateException("targer必须是targerinterface接口的实现类");
        }

        return (T)Proxy.newProxyInstance(targer.getClass().getClassLoader(), targer.getClass().getInterfaces(),new CosTimeInvocationHandler(targer));
    }
}
