package com.linkjb.servicebase.service.Impl;

import com.linkjb.servicebase.Threads.SubThread;
import com.linkjb.servicebase.pojo.LinkMedia;
import com.linkjb.servicebase.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

@Service
public class UserServiceImp implements UserService, BeanFactoryAware {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    //用于从IOC里取对象
    private BeanFactory factory;//如果实现Runnable的类是通过spring的application.xml文件进行注入,可通过 factory.getBean()获取
    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 2;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;
    /*
        用于存储在队列中的orderId,防止重复提交,在显示场景中,可用redis代替 验证重复
     */
    Map<String,Object> catcheMap = new ConcurrentHashMap<>();
    /*
        orderId的缓冲队列,当线程池满了,将orderId插入到此缓冲队列
     */
    Queue<Object> msgQueue = new LinkedBlockingDeque<>();
    /**
            * 当线程池的容量满了，执行下面代码，将orderId存入到缓冲队列
     */
    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            msgQueue.offer(((SubThread)r).getSubId());
            log.info("系统任务太忙了,把此orderId交给(调度线程池)逐一处理，id号：" + ((SubThread) r).getSubId());

        }
    };
    /**创建线程池*/
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue(WORK_QUEUE_SIZE),
            this.handler);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        factory = beanFactory;
    }
    @Override
    public List<LinkMedia> getLinkMedia(String orderId) {
        log.info("此orderId准备添加到线程池，orderId号：" + orderId);
        //验证当前进入的orderId是否已经存在
        if(catcheMap.get(orderId) == null){
            catcheMap.put(orderId,new Object());
            SubThread subThread = new SubThread(orderId);
            threadPool.execute(subThread);
        }
        return null;
    }
    /**
     * 线程池的定时任务----> 称为(调度线程池)。此线程池支持 定时以及周期性执行任务的需求。
     */
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    /**
     * 检查(调度线程池)，每秒执行一次，查看orderId的缓冲队列是否有 订单记录，则重新加入到线程池
     */
    final ScheduledFuture scheduledFuture = scheduler.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            //判断缓冲队列是否存在记录
            if(!msgQueue.isEmpty()){
                //当线程池的队列容量少于WORK_QUEUE_SIZE，则开始把缓冲队列的id 加入到 线程池
                if(threadPool.getQueue().size() < WORK_QUEUE_SIZE){
                    String orderId = (String) msgQueue.poll();
                    SubThread subThread = new SubThread(orderId);
                    log.info(("(调度线程池)缓冲队列出现订阅业务，重新添加到线程池，id号："+orderId));
                }
            }
        }
    },0, 1, TimeUnit.SECONDS);
    /**获取消息缓冲队列*/
    public Queue<Object> getMsgQueue() {
        return msgQueue;
    }

    /**终止订单线程池+调度线程池*/
    public void shutdown() {
        //true表示如果定时任务在执行，立即中止，false则等待任务结束后再停止
        log.info("终止订阅线程池+调度线程池："+scheduledFuture.cancel(false));
        scheduler.shutdown();
        threadPool.shutdown();

    }


}
