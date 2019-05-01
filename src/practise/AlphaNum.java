package practise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class AlphaNum {

    volatile Boolean flag=true;
    public static void main(String[] args) throws InterruptedException {


        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

        AtomicBoolean flag = new AtomicBoolean(Boolean.TRUE);

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for (int i = 1; i <= 26; i++) {
                        synchronized (lock) {
                            while (flag.get()) {
                                lock.wait();
                            }
                            System.out.println(i);
                            flag.compareAndSet(Boolean.FALSE,Boolean.TRUE);
                            lock.notify();
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
                    for (char c = 'A'; c <= 'Z'; c++) {
                        synchronized (lock) {
                            while (!flag.get()) {
                                lock.wait();
                            }
                            System.out.println(c);
                            flag.compareAndSet(Boolean.TRUE,Boolean.FALSE);
                            lock.notify();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        t1.start();
        t2.start();

    }
}