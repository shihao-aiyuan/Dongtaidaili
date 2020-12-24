package juc;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author shihao
 * @create 2020-09-03 18:05
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);

        for (int i=0; i<6;i++){
           new Thread(() -> {
               try {
                   semaphore.acquire();
                   System.out.println("抢到车位");
                   try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                   System.out.println("停车3秒, 离开");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   semaphore.release();
               }
           }, "thread name").start();
        }
    }


}
