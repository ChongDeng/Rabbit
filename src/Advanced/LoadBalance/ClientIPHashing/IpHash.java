package Advanced.LoadBalance.ClientIPHashing;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IpHash {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.1.93");
        add("192.168.1.108");
        //add("192.168.1.4");
    }};
    public static String getConnectionAddress() throws UnknownHostException {
        int ipHashCode = InetAddress.getLocalHost().getHostAddress().hashCode();
        int pos = ipHashCode % list.size();
        return list.get(pos);
    }
}