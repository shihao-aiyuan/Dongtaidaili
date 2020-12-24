import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shihao
 * @create 2020-11-10 15:49
 */
public class AtommicTest {

    private static int count=0;
    private static  AtomicInteger atomicInteger=new AtomicInteger(0);

    private  static  AtomicInteger atomicIntegers=new AtomicInteger(Integer.MAX_VALUE);


    @Test
    public void test() throws Exception{
        //创建线程池
        ScheduledThreadPoolExecutor scheduled=new ScheduledThreadPoolExecutor(200);
        for (int i = 0; i < 1000 ; i++) {
            scheduled.execute(() ->{
                for (int j = 0; j < 2; j++) {
                    System.out.println("线程:"+Thread.currentThread().getName()+"count=" + count++);
                }
            });
        }
        scheduled.shutdown();
        Thread.sleep(1000);
        System.out.println("最终值: " +count);
    }



    @Test
    public void test2() throws Exception{
        //创建线程池
        ScheduledThreadPoolExecutor scheduled=new ScheduledThreadPoolExecutor(200);
        for (int i = 0; i < 1000 ; i++) {
            scheduled.execute(() ->{
                for (int j = 0; j < 2; j++) {
                    //自增并返回当前值
                    int incrementAndGet = atomicInteger.incrementAndGet();
                    System.out.println("线程:"+Thread.currentThread().getName()+"count=" + incrementAndGet);
                }
            });
        }
        scheduled.shutdown();
        Thread.sleep(1000);
        System.out.println("最终值: " +atomicInteger.get());
    }

    /**
     * AtomicInteger的越界问题
     */
    @Test
    public void  test3(){
        System.out.println("当前值: "+ atomicIntegers);
        for (int i = 0; i <10 ; i++) {
            System.out.println("当前值: "+ atomicIntegers.incrementAndGet());
        }

        CopyOnWriteArrayList copyOnWriteArrayList=new CopyOnWriteArrayList();
        ConcurrentHashMap map=new ConcurrentHashMap();
    }
}
