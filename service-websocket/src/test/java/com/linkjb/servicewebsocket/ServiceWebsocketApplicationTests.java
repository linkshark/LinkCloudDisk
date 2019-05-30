package com.linkjb.servicewebsocket;

import com.alibaba.fastjson.JSONObject;
import com.linkjb.servicewebsocket.conf.mq.RabbitMqConfig;
import com.linkjb.servicewebsocket.entity.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceWebsocketApplicationTests {
    Logger log = LoggerFactory.getLogger(ServiceWebsocketApplicationTests.class);

    List<String> list = Arrays.asList("Java","jjui", "JavaScript", "python", "PHP", "C#", "Golang", "Swift");
    List<Dish> DishList = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public enum CaloricLevel { DIET, NORMAL, FAT };


    //@Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }
    @Test
    public void Test02() throws InterruptedException {
        while(true){
            Thread.sleep(5000);
            JSONObject s = new JSONObject();
            s.put("name","shark");
            //a
            String uuid = UUID.randomUUID().toString();
            CorrelationData correlationId = new CorrelationData(uuid);
            System.out.println("发送方发送消息,消息为:"+s.toJSONString());
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY3,s.toJSONString(),correlationId);
            //rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY1,s.toJSONString(),correlationId);

        }
    }

    @Test
    public void TopicTest(){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);

        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic.info","向topic.info发送了消息",correlationId);
        log.info("向topic.info发送了消息");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic","向topic发送了消息",correlationId);
        log.info("向topic发送了消息");
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPICEXCHANGE,"topic.error","向topic.error发送了消息",correlationId);
        log.info("向topic.error发送了消息");

    }

    @Test
//    https://mrbird.cc/java8stream1.html
    public void Test03(){
//        List<String> a = new LinkedList<>();
//        Runnable b = () ->
//            a.add("hello lamdba");
//
//        b.run();
//        log.info(a.toString());
//
//        String str = "hello ";
        List<String> a = new ArrayList<>();
        Predicate<Integer> isEven = (i) -> i%2==0;
        Predicate<String> isEmpty =  String::isEmpty;
//        Boolean test = isEven.test(5);
//        Function<String,String> upperCase = String::toUpperCase;
//        List<String> testList = Arrays.asList("esrhifuiANkjnjkNOIN","sirufbesirgbAisedngkj","ndsuirfgbnuiwerbgiu");
//        testList.stream().map(i -> upperCase.apply(i));
//        log.info(testList.toString());
        Boolean test = isEven.and((in) -> in > 30).test(20);
        log.info(test.toString());
    }
    @Test
    public void StreamTest(){
        //list.stream().filter(s -> s.startsWith("j")||s.startsWith("J")).map(String::toUpperCase).forEach(System.out::println);
//        Streams接口支持·filter方法，该方法接收一个Predicate<T>，函数描述符为T -> boolean，用于对集合进行筛选，返回所有满足的元素：
        //list.stream().filter(s -> s.contains("#")).forEach(System.out::println);



//        distinct方法用于排除流中重复的元素，类似于SQL中的distinct操作。比如筛选中集合中所有的偶数，并排除重复的结果：
//        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//        numbers.stream().distinct().forEach(s->log.info(s.toString()));
    }
    @Test
    public void StreamTest2() {
//        skip(n)方法用于跳过流中的前n个元素，如果集合元素小于n，则返回空流。比如筛选出以J开头的元素，并排除第一个：
//        list.stream()
//                .filter(s -> s.startsWith("J"))
//                .skip(1)
//                .forEach(System.out::println);
//    }

//    limit(n)方法返回一个长度不超过n的流，比如下面的例子将输出Java JavaScript python
//        list.stream()
//                .limit(3)
//                .forEach(System.out::println);


    }

    @Test
    public void StreamTest3() {
//        map方法接收一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素。如：
        //list.stream().map(String::hashCode).forEach(System.out::println);

//        map还支持将流特化为指定原始类型的流，
//        如通过mapToInt，mapToDouble和mapToLong方法，可以将流转换为IntStream，DoubleStream和LongStream。
//        特化后的流支持sum，min和max方法来对流中的元素进行计算。比如：
//        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
//       System.out.println(numbers.stream().mapToInt(a -> a).sum());

//        也可以通过下面的方法，将IntStream转换为Stream：
//        IntStream intStream = numbers.stream().mapToInt(a -> a);
//        Stream<Integer> s = intStream.boxed();

    }

    @Test
    public void StreamTest4() {
//        flatMap用于将多个流合并成一个流，俗称流的扁平化。这么说有点抽象，举个例子，比如现在需要将list中的各个元素拆分为一个个字母，并过滤掉重复的结果，你可能会这样做：
        //list.stream().map(s ->s.split("")).distinct().forEach(System.out::println);
//        syso
//        [Ljava.lang.String;@44641d6c
//                [Ljava.lang.String;@1ae924f1
//                [Ljava.lang.String;@59d5a6fd
//                [Ljava.lang.String;@27bcb4ad
//                [Ljava.lang.String;@4357524b
//                [Ljava.lang.String;@104a287c
//                [Ljava.lang.String;@64dc86c6
//                [Ljava.lang.String;@26874f2c

//        这明显不符合我们的预期。实际上在map(s -> s.split(""))操作后，返回了一个Stream<String[]>类型的流，所以输出结果为每个数组对象的句柄，而我们真正想要的结果是Stream<String>！
//
//        在Stream中，可以使用Arrays.stream()方法来将数组转换为流，改造上面的方法：

        //list.stream().map(s -> s.split("")).map(Arrays::stream).distinct().forEach(System.out::println);
//        java.util.stream.ReferencePipeline$Head@aca3c85
//        java.util.stream.ReferencePipeline$Head@45eab322
//        java.util.stream.ReferencePipeline$Head@f017dd0
//        java.util.stream.ReferencePipeline$Head@2424cb9d
//        java.util.stream.ReferencePipeline$Head@7f51f588
//        java.util.stream.ReferencePipeline$Head@6fb22ae3
//        java.util.stream.ReferencePipeline$Head@6bd92538
//        java.util.stream.ReferencePipeline$Head@69a373fd

//        因为上面的流经过map(Arrays::stream)处理后，将每个数组变成了一个新的流，
//        返回结果为流的数组Stream<String>[]，所以输出是各个流的句柄。
//        我们还需将这些新的流连接成一个流，使用flatMap来改写上面的例子：
        list.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().forEach(s -> System.out.println(s));

    }

    @Test
    public void StreamTest5() {
       //list.stream().anyMatch(s -> s.contains("Java"))
        //list.stream().filter(s -> s.startsWith("j")).findAny().ifPresent(System.out::println);

//        reduce函数从字面上来看就是压缩，缩减的意思，它可以用于数字类型的流的求和，求最大值和最小值。如对numbers中的元素求和：
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .reduce(0, Integer::sum); // 16
//        reduce函数也可以不指定初始值，但这时候将返回一个Optional对象，比如求最大值和最小值：
        numbers.stream()
                .reduce(Integer::max)
                .ifPresent(System.out::println); // 4

        numbers.stream()
                .reduce(Integer::min)
                .ifPresent(System.out::println); // 1
    }
    @Test
    public void StreamTest6(){
//        collect方法用于收集流中的元素，并放到不同类型的结果中，比如List、Set或者Map。举个例子：
        List<String> collect = list.stream().filter(s -> s.startsWith("j") || s.startsWith("J")).collect(Collectors.toList());
        log.info(collect.toString());
    }
    @Test
    public void StreamTest7(){
        long wordCout = 0L;
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\shark\\Desktop\\file.txt"), Charset.defaultCharset())) {
            wordCout = lines.map(l -> l.split(""))
                    .flatMap(Arrays::stream)
                    .count();
            System.out.println(wordCout);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }



    @Test
    public void ColleactTest01(){
//        Collectors.maxBy和Collectors.minBy用来计算流中的最大或最小值，比如按卡路里的大小来筛选出卡路里最高的食材：
        //DishList.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);

//        Collectors.summingInt可以用于求和，参数类型为int类型。相应的基本类型对应的方法还有Collectors.summingLong和Collectors.summingDouble。比如求所有食材的卡路里：
//        Integer collect = DishList.stream().collect(summingInt(Dish::getCalories));
//        log.info(collect.toString());

//        collectors.averagingInt方法用于求平均值，参数类型为int类型。相应的基本类型对应的方法还有Collectors.averagingLong和Collectors.averagingDouble。
//        比如求所有食材的平均卡路里:

//        Double collect = DishList.stream().collect(averagingInt(Dish::getCalories));
//        log.info(collect.toString());

//        Collectors.summarizingInt方法可以一次性返回元素个数，最大值，最小值，平均值和总和：
        //Integer collect = DishList.stream().collect(summingInt(Dish::getCalories));
        IntSummaryStatistics iss = DishList.stream().collect(summarizingInt(Dish::getCalories));
        log.info(iss.toString());

    }


    @Test
    public void CollectTest02(){
//        Collectors.joining方法会把流中每一个对象应用toString方法得到的所有字符串连接成一个字符串。如：
//        String collect = DishList.stream().map(s -> s.getName()).collect(joining(","));
//        log.info(collect);
//
//        //Collectors.groupingBy方法可以轻松的完成分组操作。比如现在对List中的食材按照类型进行分组：
//        Map<Dish.Type, List<Dish>> collect1 = DishList.stream().collect(groupingBy(Dish::getType));
//        log.info(collect1.toString());

//        我们也可以自定义分组规则，比如按照卡路里的高低分为高热量，正常和低热量：
        Map<CaloricLevel, List<Dish>> collect = DishList.stream().collect(groupingBy(d ->
                {
                    if (d.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }
        ));
        log.info(collect.toString());


    }

    @Test
    public void OptionalTest(){
//        Department d =  new Department()
    }



}
class Department {
    private Employee employee;
    public Department(Employee employee) {
        this.employee = employee;
    }
    Employee getEmployee() {
        return employee;
    }
}

class Employee {
    private Girl girlFriend;
    public Employee(Girl girlFriend) {
        this.girlFriend = girlFriend;
    }
    Girl getGirlFriend() {
        return girlFriend;
    }
}

class Girl {
    private String name;
    public Girl(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
