package lock;

/**
 * @author shihao
 * @create 2020-09-04 17:06
 */
public class LockTest {
    //线程  操作  资源类
    //判断  处理   通知(唤醒)

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //shareResource.print5();
                shareResource.prints(1);
            }
        }, "thread name").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //shareResource.print6();
                shareResource.prints(2);
            }
        }, "thread name").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //shareResource.print7();
                shareResource.prints(3);
            }
        }, "thread name").start();


//        for (int i=1; i<=3;i++){
//            final  int temp=i;
//            new Thread(() -> {
//
//                for (int j =0 ; j < 10; j++) {
//                    shareResource.prints(temp);
//
//                }
//
//            }, "thread name").start();
//        }
   }




}
