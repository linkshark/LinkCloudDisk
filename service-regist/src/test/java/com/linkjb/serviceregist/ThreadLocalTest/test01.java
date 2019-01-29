package com.linkjb.serviceregist.ThreadLocalTest;

/**
 * @author sharkshen
 * @data 2019/1/29 16:31
 * @Useage
 */
public class test01 {
    //通过匿名内部类覆盖ThreadLocal 的默认方法,指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0 ;
        }
    };

    public int getNextNum(){////获取下一个序列值
        seqNum.set(seqNum.get()+1);
        return seqNum.get();

    };
    public static void main(String[] args){
        test01 sn =  new test01();
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();

    }
    public static class TestClient extends Thread{
        private test01 sn;
        public TestClient(test01 sn){
            this.sn = sn ;
        }
        public void run(){
            for(int i= 0 ;i < 3;i++ ){
                System.out.println("Thread:"+Thread.currentThread().getName()+"  sn:"+sn.getNextNum());
            }
        }
    }


}
