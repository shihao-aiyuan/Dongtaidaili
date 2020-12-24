package cglib;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.*;

import javax.xml.ws.Service;
import java.lang.reflect.Method;

/**
 * @author shihao
 * @create 2020-09-02 11:09
 */
public class CglibTest {


    @Test
    public void test1(){
        //使用Enhancer来给某个类创建代理类 步骤:
        //1.创建Enhancer对象
        Enhancer enhancer=new Enhancer();

        //2.通过setSuperClass 来设置父类型 , 既需要给那个类创建代理类
        enhancer.setSuperclass(Service1.class);

        /*3.设置回调, 需要实现org.springframework.cglib.proxy.callback接口
        此处我们调用org.springframework.chlib.proxy.MethodInterceptor接口
        当调用代理对象的任何放法的时候, 都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 代理对象方法拦截器
             * @param o     代理对象
             * @param method   被代理类的方法, 即Service1的方法
             * @param objects  调用方法传递发参数
             * @param methodProxy   方法代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                System.out.println("调用方法:"+ method);
                //可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });

        //获取代理对象, 调用enhancer.create方法获取代理对象.  返回值是Object类型
        Service1  proxy = (Service1) enhancer.create();

        //5.调用代理对象的方法
        proxy.m1();
        proxy.m2();

    }

    @Test
    public void test2(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" +method.getName());
                Object aSuper = methodProxy.invokeSuper(o, objects);
                return aSuper;
            }
        });

        Service2 service2 = (Service2) enhancer.create();

        service2.m1();
        service2.m2();

    }

    /**
     * 全拦截
     */
    @Test
    public void test3(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Service3.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                return "我是拦截方法";
            }
        });

        Service3 service3 = (Service3) enhancer.create();

        System.out.println(service3.m1());
        System.out.println(service3.m2());
        System.out.println(service3.toString());
    }

    /**
     * 全放开
     */
    @Test
    public void test4(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 service3 = (Service3) enhancer.create();
        System.out.println(service3.m1());
        System.out.println(service3.m2());
    }


    /**
     * 分类拦截
     */
    @Test
    public void  test5(){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Service4.class);
        //创建一个callback
        Callback[] callbacks={
            //拦截insert方法
            new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    long stratTime = System.nanoTime();
                    Object aSuper = methodProxy.invokeSuper(o, objects);
                    long endTime = System.nanoTime();
                    System.out.println(method+"耗时:"+(endTime-stratTime));
                    return aSuper;
                }
            },
            //拦截get方法
            new FixedValue() {
                @Override
                public Object loadObject() throws Exception {
                    return "我是get方法";
                }
            }
        };

        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         * 返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                //获取当前调用的方法的名称
                String name = method.getName();
                /**
                 * 方法名称以insert开头，
                 * 返回callbacks中的第1个Callback对象来处理当前方法，
                 * 否则使用第二个Callback处理被调用的方法
                 */
                return name.startsWith("Insert") ? 0:1;
            }
        });

        Service4 service4=(Service4) enhancer.create();
        service4.Insert1();
        service4.Insert2();

        String service41 = service4.get1();
        System.out.println(service41);
        System.out.println(service4.get2());
    }


    @Test
    public void test6(){
        Enhancer enhancer=new Enhancer();

        //创建2个callback
        final Callback costTimeCallback = (MethodInterceptor) (Object o, Method m, Object[] objects, MethodProxy methodProxy) ->{
            long stratTime = System.nanoTime();
            Object aSuper = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(m+"耗时:"+(endTime-stratTime));
            return aSuper;
        };

        //拦截get开头的方法, 返回固定值
        final Callback fixdValuecallback=(FixedValue) () ->"我是get方法";

        CallbackHelper callbackHelper=new CallbackHelper(Service4.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("Insert") ? costTimeCallback :fixdValuecallback;
            }
        };

        enhancer.setSuperclass(Service4.class);

        //调用enhancer的setcallback传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //设置CallbackFilter, 用来判断某个方法具体那个Callback
        enhancer.setCallbackFilter(callbackHelper);

        Service4 service4 =(Service4) enhancer.create();

        System.out.println("---------------");
        service4.Insert1();
        System.out.println("---------------");
        service4.Insert2();
        System.out.println("---------------");
        System.out.println(service4.get1());
        System.out.println("---------------");
        System.out.println(service4.get2());

    }

    @Test
    public void test7(){
        Service1 service1 = CostTimeProxy.createProxy(new Service1());
        service1.m1();

        Service4 service4 = CostTimeProxy.createProxy(new Service4());
        service4.Insert1();
        System.out.println(service4.get1());
    }
}
