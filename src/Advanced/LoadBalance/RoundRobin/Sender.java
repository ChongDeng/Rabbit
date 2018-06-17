package Advanced.LoadBalance.RoundRobin;
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

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import Utils.ConnextionUtil;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by chong
 */
public class Sender {
    private final static String QUEUE = "MQ_RoundRobin4";//队列的名字

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 5; ++i){

            String ServerIP = RoundRobin.getConnectionAddress();

            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(ServerIP);//设置 server 的地址
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("root");
            connectionFactory.setPassword("root123");
            //获取连接
            Connection connection = connectionFactory.newConnection();//创建一个新的连接
            //创建通道
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE, false, false, false, null);

            //发送内容
            channel.basicPublish("",QUEUE,null,("今晚聚餐 黑巴扎黑" + i).getBytes("UTF-8"));

            //关闭连接
            channel.close();
            connection.close();
        }
    }
}
