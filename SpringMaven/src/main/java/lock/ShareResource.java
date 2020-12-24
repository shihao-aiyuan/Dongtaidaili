package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shihao
 * @create 2020-09-04 17:08
 */
public class ShareResource {
    private  int number=1;   // A:1 ,B:2, C:3
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    private Condition c4=lock.newCondition();


    public   void print5(){

        lock.lock();
        try {
            while (number !=1){
                c1.await();
            }

            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t" +"打印5次");
            }
            number=2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void print6(){

        lock.lock();
        try {
            while (number !=2){
                c2.await();
            }

            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t" +"打印6次");
            }
            number=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void print7(){

        lock.lock();
        try {
            while (number !=3){
                c3.await();
            }

            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t" +"打印7次");
            }
            number=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void prints(int i){

        lock.unlock();
        int j=1;
        try {


                while (i!=j){
                    if(i!=1){
                        c1.await();
                    }else if(i!=2){
                        c2.await();
                    }else{
                        c3.await();
                    }

                }

                for (int k = 0; k <5 ; k++) {
                    System.out.println("我是整合的方法"+i);
                }

                if(i==1){
                    j=2;
                    c2.signal();
                }else if(i==2){
                    j=3;
                    c3.signal();
                }else if(i==3){
                    j=1;
                    c1.signal();
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}
