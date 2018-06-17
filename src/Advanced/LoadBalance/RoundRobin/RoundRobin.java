package Advanced.LoadBalance.RoundRobin;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.1.93");
        add("192.168.1.108");
        //add("192.168.1.4");
    }};
    private static int pos = 0;
    private static final Object lock = new Object();
    public static String getConnectionAddress(){
        String ip = null;
        synchronized (lock) {
            ip = list.get(pos);
            if (++pos >= list.size()) {
                pos = 0;
            }
        }
        return ip;
    }
}