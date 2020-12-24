package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author shihao
 * @create 2020-09-03 17:20
 */
public class CyclicBarrierTest {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,() -> { System.out.println("召唤神龙"); });

        for (int i=1; i <=7; i++){
            final int temp=i;
            new Thread(() -> {
                System.out.println("收集到第"+ temp +"龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, Thread.currentThread().getName()).start();
        }
    }

}
