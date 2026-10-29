
import java.util.HashMap;
import java.util.Map;

public class most {

    static String[] mostPopular(String[] orders) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int maxCount = count.get(bestItem);

        for (String item : orders) {
            if (count.get(item) > maxCount) {
                maxCount = count.get(item);
                bestItem = item;
            }
        }

        return new String[]{bestItem, String.valueOf(maxCount)};
    }

    public static void main(String[] args) {
        String[] orders1 = {
            "dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"
        };

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};

        String[] r1 = mostPopular(orders1);
        String[] r2 = mostPopular(orders2);

        System.out.println("(" + r1[0] + ", " + r1[1] + ")");
        System.out.println("(" + r2[0] + ", " + r2[1] + ")");
    }
}
