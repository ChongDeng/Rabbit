package WorkQueue;

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
 * Created by chong
 */
public class Worker2 {
    private final static String QUEUE = "MQ_WORK";//队列的名字


    public static void main(String[] args) throws  Exception {

        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE,false,false,false,null);
        channel.basicQos(1);//告诉服务器,在我们没有确认当前消息完成之前,不要给我发新的消息

        DefaultConsumer consumer =new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //当我们收到消息的时候调用
                System.out.println("消费者2 收到的内容是:"+new String(body));
                try {
                    Thread.sleep(2000);//模拟耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //确认; 参数2: false 为确认收到消息; true 为拒接收到消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //注册消费者, 参数2:false为手动确认,代表我们收到消息后需要手动告诉服务器,我收到消息了
        channel.basicConsume(QUEUE,false,consumer);
    }
}
