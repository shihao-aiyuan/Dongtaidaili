package lock;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class myResource{

    private volatile   boolean flag=true;  //默认开启
    private AtomicInteger atomicInteger=new AtomicInteger();


    BlockingQueue<String> blockingQueue=null;

    public myResource(BlockingQueue<String> blockingQueue){

        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws  Exception{

        String data=null;
        boolean offer=true;

        while (flag){
            data = atomicInteger.incrementAndGet() + "";
            offer = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if(offer){
                System.out.println("我正在赛数据, 赛成功了!");
            }else{
                System.out.println("我被赛满了, 赶紧取啊!");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("叫停了, 别赛了");
    }


    public void myCousmter() throws  Exception{

        String poll=null;

        while (flag){
            poll= blockingQueue.poll(2L, TimeUnit.SECONDS);

            if(null==poll||poll.equalsIgnoreCase("")){
                flag=false;
                System.out.println("我已经空了, 别取了!");

                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }else{
                System.out.println("我正在取数据, 取成功了!");

            }
        }

        System.out.println("叫停了, 别取了");
    }


    public void stop() throws  Exception{
        this.flag=false;
    }
}




/**
 * @author shihao
 * @create 2020-09-04 18:15
 */
public class ProdConsumerTest {


    public static void main(String[] args) {
        myResource myResource=new myResource(new ArrayBlockingQueue<String>(5));

        new Thread(() -> {
            try {
                myResource.myProd();

                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "prod").start();

        new Thread(() -> {
            try {
                myResource.myCousmter();

                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "prod").start();


        try {
            TimeUnit.SECONDS.sleep(5);

            myResource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
