package Topic;

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
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Created by Chong
 */
public class Sender {
    private final static String EXCHANGE_NAME = "EX_Topic";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnextionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange,声明为 topic 也就是通配符类型
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //发送usa.west.news数据,凡是能匹配到这个关键词的都会收到
        channel.basicPublish(EXCHANGE_NAME, "usa.west.news", null, "消息为usa west news".getBytes());
        channel.close();
        connection.close();
    }
}
