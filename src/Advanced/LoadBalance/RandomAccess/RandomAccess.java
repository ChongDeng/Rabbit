package Advanced.LoadBalance.RandomAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAccess {
    private static List<String> list = new ArrayList<String>(){{
        add("192.168.0.2");
        add("192.168.0.3");
        add("192.168.0.4");
    }};
    public static String getConnectionAddress(){
        Random random = new Random();
        int pos = random.nextInt(list.size());
        return list.get(pos);
    }
}