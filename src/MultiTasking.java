import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiTasking {

    public static void main(String ... args) {



        Object obj = new Object();


        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.notifyAll();

                }
            }
        });

        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Wait over");
                }
            }
        });

        Thread t3= new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("My Wait also over");
                }
            }
        });

        t2.start();
        t3.start();
        t1.start();


        BlockingQueue<String> queue = new LinkedBlockingQueue<>(1);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String temp="";
                for (int i = 1; i <=100; i++) {
                    temp+=i;
                    if(i%3==0) {
                        try {
                            synchronized (queue){
                                queue.put(temp);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (char i = 'A'; i <='Z'; i++) {
                    try {
                        synchronized (queue) {
                            queue.put(i + "");
                        }
                        System.out.println(+i+"PUT");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                String output = "";
                for(int i=0;i<=25;i++){
                    try {
                        output+=queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                    }
                }
                System.out.println(output);

            }
        });


    }
}
