package com.jyh.sixthspace.sdk.utlis;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jyh on 2016/7/11.
 */
public class ThreadManager {
    private static ThreadManager manager = null;
    // 系统最大线程数量
    private static int NUMBER_OF_CORES =
            Runtime.getRuntime().availableProcessors();
    private ThreadManager() {
    }

    private ThreadPoolExecutor  threadPoolExecutor;
    private  BlockingQueue<Runnable> workQueue;
    public static ThreadManager getInstance() {
        if (manager == null) {
            manager = new ThreadManager();
        }
        return manager;
    }
    public  void addRun(Runnable run){
        creatThreadPool();
        threadPoolExecutor.execute(run);
    }

    public void creatThreadPool(){
      if (threadPoolExecutor==null){
          /**
           * 创建的等待的序列，按照先进先出顺序 10000是最大等待数量
           * */
          workQueue=new ArrayBlockingQueue<Runnable>(10000);
          threadPoolExecutor=new ThreadPoolExecutor(
                  3,//固定线程池大小
                  3,//最大线程池大小
                  1,//如果最大线程数量大于固定线程数量，且有空闲的线程。超过1秒者回收
                  TimeUnit.SECONDS,
                  workQueue);//创建的等待队列
      }
    }
    public BlockingQueue<Runnable> getBlockingQueue(){
        return workQueue;
    }
}
