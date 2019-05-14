package com.company.mytest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    public static ExecutorService newFixedThreadPool(int nThread){
        return new ThreadPoolExecutor(nThread,nThread,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    }

    static class MyTask implements Runnable{
        private String name;
        private MyTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try{
                System.out.println(this.toString()+" is running!");
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString(){
            return "My Task [name= "+ name + "]";
        }
    }
    static class MyThreadFactory implements ThreadFactory{
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r,"my-thread-"+mThreadNum.getAndIncrement());
            System.out.println(thread.getName()+" has been created.");
            return thread;
        }
    }
    static class MyIgnorePolicy implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r,executor);
        }
        private void doLog(Runnable r, ThreadPoolExecutor executor){
            System.out.println(r.toString() +" rejecued.");
        }
    }

    public static void main(String[] args){
        int corePoolSize = 2;
        int maximunPoolSize = 5;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3);
        ThreadFactory threadFactory = new MyThreadFactory();
        RejectedExecutionHandler handle = new MyIgnorePolicy();
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize,maximunPoolSize,keepAliveTime,unit,workQueue,threadFactory,handle);

        for(int i=1;i<10;i++){
            MyTask myTask = new MyTask(String.valueOf(i));
            executorService.execute(myTask);

        }
        executorService.shutdown();
    }

}
