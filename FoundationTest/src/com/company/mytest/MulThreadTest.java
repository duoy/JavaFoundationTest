package com.company.mytest;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyRunnale implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
public class MulThreadTest {
    private int cnt;        //多个线程对同一共享资源进行访问而步进行同步操作，那么操作的结果是不一致的
    //private AtomicInteger cnt = new AtomicInteger();    //可将int换为AtomicInteger类型，就变为线程安全
    public void incCnt(){
        //cnt++;
        //cnt.incrementAndGet();
    }
    public int getCnt(){
        return cnt;
        //return cnt.get();
    }
    //或者对访问共享资源的方法进行加锁
    public synchronized void incSynCnt(){
        cnt++;
    }
    public int getSynCnt(){
        return cnt;
    }
    /**
     * 测试synchronized
     * 1.同步一个代码块（同一对象使用才进行同步）
     * 2.同步一个方法（同一对象时才进行同步）
     * 3.同步一个类
     * 4.同步静态方法
     */
    public void func1(){
        //同步代码块
        synchronized (this){
            for(int i =0;i<10;i++){
                System.out.print(i+" ");
            }
        }
    }
    //同步一个方法
    public synchronized void fun2(){

    }
    public void fun3(){
        // 同步一个类
        synchronized (MulThreadTest.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }
    //同步一个静态方法
    public static synchronized void fun(){

    }

    /**
     * ReentrantLock
     * AQS中对Exclusive独占资源的访问方式
     */
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public  void testReentrantLock(){
        lock.lock();
        try{
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }finally{
            lock.unlock();
        }
    }

    /**
     * wait() notify() notifyAll()
     * 都属于Object的一部分，而不属于Thread
     * 只能在同步方法或同步控制快中使用，否则会抛出IIIegalMonitorStateException
     * 使用wait()挂起其期间，线程会释放锁。这是因为如果没有释放锁，其他线程就无法进入对象的同步方法或同步控制块中，
     * 那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁
     */
    public synchronized void before(){
        System.out.println("before");
        notifyAll();
    }
    public synchronized  void after(){
        try{
            wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("after");
    }

    /**
     * await() signal() signalAll()
     * J.U.C提供Condition类来实现线程之间的协调。可以在condition上调用await()使线程等待，其它线程调用signal()或signalAll()来唤醒等待的线程
     * Condition condition = lock.newCondition()
     */
    public void before1(){
        lock.lock();
        try{
            System.out.println("before1");
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void after1(){
        lock.lock();
        try{
            condition.await();
            System.out.println("after1");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 使用 BlockingQueue 实现生产者消费者问题
     */
    //public static BlockingDeque<String> queue = new ArrayBlockingQueue<>(5);  //Deque:双端队列
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
    private static class Producer extends Thread{
        @Override
        public void run(){
            try{
                queue.put("product");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.print("produce..");
        }
    }
    private static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("consume..");
        }
    }

    private final static int totalThread = 240;

    /**
     * 用于输出线程ID
     * @param threadNum
     * @throws InterruptedException
     */
    public static void outputThreadNum(int threadNum)throws InterruptedException{
        Thread.sleep(1000);
        System.out.println("threadNum is : "+threadNum+" is running..");
        Thread.sleep(1000);
    }
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);    //10个线程为一组进行同步
    private static final CyclicBarrier cyclicBarrier1 = new CyclicBarrier(5, () -> {
        System.out.println("------当线程数达到之后，优先执行------");
    });

    public static void outputThreadNum1(int threadNum) throws InterruptedException,BrokenBarrierException{

        System.out.println("threadnum:" + threadNum + " is ready");
        cyclicBarrier.await();
        System.out.println("threadnum:" + threadNum + "is finish");
        //Thread.sleep(1000);
    }
    public static void main(String[] args){
        /**
         * executorService.execute(new MyRunnale());
         */
        /*
        ExecutorService executorService = Executors.newCachedThreadPool();
        long stratTime = System.currentTimeMillis();
        for(int i=0;i<9;i++){
            executorService.execute(new MyRunnale());
        }
        long endTime = System.currentTimeMillis();
        executorService.shutdown();
        System.out.println(endTime-stratTime);
        */

        /**
         * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
         *以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
         */
        /*
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{   //使用Lambda创建线程，相当于创建了一个匿名内部类
            try{
                Thread.sleep(2000);
                System.out.println("Thread run");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        System.out.println("Main run");
        */


        MulThreadTest mulThreadTest = new MulThreadTest();
        MulThreadTest mulThreadTest1 = new MulThreadTest();
        //ExecutorService executorService = Executors.newCachedThreadPool();
        //调用同一个对象的同步代码块，两个线程会进行同步，当一个线程进入同步代码块时，另一个线程就必须等待
        //executorService.execute(() -> mulThreadTest.func1());
        //executorService.execute(mulThreadTest::func1);  //remove Lambda with method reference
        //两个线程调用不同对象的同步代码块，因此这两个线程就不需要同步，冲输出结果看出，两个线程交叉执行
        //executorService.execute(()->mulThreadTest1.func1());
        //使用ReentrantLock进行同步
        //executorService.execute(mulThreadTest1::testReentrantLock);

        //executorService.execute(mulThreadTest1::after);
        //executorService.execute(mulThreadTest1::before);

        //executorService.execute(mulThreadTest1::after1);
        //executorService.execute(mulThreadTest1::before1);
/**
 * CountDownLatch 用来控制一个线程等待多个线程。
 *维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。
 */

        final int threadNum = 600;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(100);
        ExecutorService executorService2 = new ThreadPoolExecutor(100,300,5,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());

        System.out.println("the count of CountDownLatch is : "+ countDownLatch.getCount());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            final int threadId = i;
            executorService1.execute(() -> {
                try{
                    //System.out.println("the "+ threadNum+" now is running...");
                    outputThreadNum(threadId);
                    //mulThreadTest.incCnt();
                    mulThreadTest.incSynCnt();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        executorService.shutdown();
        executorService1.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("CountDownLatch 共花费： "+(endTime-startTime)/1000.0+" 秒");
        System.out.println("end...");
        //System.out.println(mulThreadTest.getCnt());
        System.out.println(mulThreadTest.getSynCnt());

/**
 *  CyclicBarrier 测试
 *  线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行
 */
        /*
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(100);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < totalThread; i++) {
            final int threadNum = i;
            try{
                Thread.sleep(1000);
                executorService.execute(() -> {
                    try {
                        outputThreadNum1(threadNum);
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("after..");
                });
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdownNow();
        long endTime = System.currentTimeMillis();
        System.out.println("CountDownLatch 共花费： "+(endTime-startTime)/1000.0+" 秒");
        System.out.println("system output...");
         */

/**
 * semaphore 测试
 * Semaphore类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 */
        /*
        final int clientCount = 3;
        final int totalRequestCount = 10;
        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalRequestCount; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.print(semaphore.availablePermits() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        executorService.shutdown();
         */

/**
 * FutureTask 可用于异步获取执行结果或取消执行任务的场景。
 * 当一个计算任务需要执行很长时间，那么就可以用 FutureTask 来封装这个任务，主线程在完成自己的任务之后再去获取结果。
  */
        /*
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(10);
                    result += i;
                }
                return result;
            }
        });
        Thread computerThread  = new Thread(futureTask);
        computerThread.start();

        Thread otherThread = new Thread(()->{
            System.out.println("other task is running...");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        otherThread.start();
        System.out.println("main output...");   //最先输出
        try{
            System.out.println(futureTask.get());
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        */

/**
 * 使用 BlockingQueue 实现生产者消费者问题
  */
/*
        for(int i=0;i<2;i++){
            Producer producer = new Producer();
            producer.start();
        }
        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        */
    }
}
