package Routing;

//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  


import Utils.ConnextionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Chong
 */
public class Recver1 {
    private final static String EXCHANGE_NAME = "EX_Routing";//定义交换机的名字
    private final static String QUEUE = "MQ_Routing1";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, false, false, false,null);
        //绑定队列到交换机
        //参数3 标记,绑定到交换机的时候会指定一个标记,只有和它一样的标记的消息才会被当前消费者收到
        // 绑定队列到交换机,绑定自己的关键字 key 为key,注意在绑定到指定路由(交换机)的时候,该路由必须存在,也就是我们必须先由发送者创建一个路由才可以
        channel.queueBind(QUEUE, EXCHANGE_NAME, "key_男人");
        //如果要接收多个标记,只需要再执行一次即可
        channel.queueBind(QUEUE, EXCHANGE_NAME, "key_帅");
        channel.basicQos(1);
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1 收到消息: " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE, false, consumer);
    }
}
