package practise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;


/**** @Description : One thread produces integers and pushes to queue
 * and other thread produces strings and pushes to queue
 * consumer thread takes one value after another from blocking queue
 */
public class Examples {

    public static void main(String[] args) throws InterruptedException {


        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

        AtomicBoolean flag = new AtomicBoolean(Boolean.TRUE);

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String alpha = "";
                    for(int i=1;i<78;i++){
                        alpha+=i;
                        if(i%3==0){
                            synchronized (lock){
                                while (flag.get()){
                                    lock.wait();
                                }
                                queue.put(alpha);
                                alpha="";
                                flag.set(Boolean.TRUE);
                                lock.notify();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(char c='A';c<'Z';c++){
                        synchronized (lock){
                            while (!flag.get()){
                                lock.wait();
                            }
                            queue.put(c+"");
                            flag.set(Boolean.FALSE);
                            lock.notify();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(2000);
                        System.out.println(queue.take()+"taken; size : "+queue.size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        t1.start();
        t2.start();
        t3.start();

    }
}

