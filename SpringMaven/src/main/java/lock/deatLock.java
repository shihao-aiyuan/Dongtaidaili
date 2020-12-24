package lock;


import java.util.concurrent.TimeUnit;

class myLock implements Runnable{

    private String lockA;
    private String lockB;

    public myLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {

        synchronized (lockA){

            System.out.println(Thread.currentThread().getName()+"尝试获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"尝试获取"+lockA);
            }
        }

    }
}



/**
 * @author shihao
 * @create 2020-09-07 18:36
 */
public class deatLock {

    //两个线程或以上的进程在执行操作的时候,因争夺资源造成相互等待的现象

    public static void main(String[] args) {
          String lockA="lockA";
          String lockB="lockB";

          new Thread(new myLock(lockA,lockB), "ThreadAAA").start();
          new Thread(new myLock(lockB,lockA), "ThreadBBB").start();

    }

    //jps    查看java的运行程序
    //jstack  进程号     查看进程信息
}
