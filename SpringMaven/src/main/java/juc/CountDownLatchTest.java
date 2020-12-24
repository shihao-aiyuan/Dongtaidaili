package juc;

import org.aspectj.runtime.internal.cflowstack.ThreadCounterImpl11;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author shihao
 * @create 2020-09-03 16:08
 */
public class CountDownLatchTest {


//    public static void main(String[] args) {
//
//        for (int i = 0; i <5; i++) {
//            new Thread(() -> {
//                System.out.println("我出去了");
//                }, String.valueOf(i)).start();
//        }
//
//        System.out.println(" 我锁门了");
//    }

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i=1; i<=6;i++){
           new Thread(() -> {
               System.out.println(Thread.currentThread().getName()+ "\t  国, 被灭");
               countDownLatch.countDown();
           }, EnumTest.forEach_Enum(i).getRetMassage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦,一统天下");

        System.out.println(EnumTest.ONE);
    }
}
