import com.mingrn.common.redis.client.JedisGeoClient;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.JedisPool;

import java.util.Properties;

public class AbstractJedisConfig extends com.mingrn.common.redis.config.AbstractJedisConfig {

    private static redis.clients.jedis.JedisPoolConfig config;

    static {
        try {
            Properties properties = PropertyUtil.loadProperties("redis.properties");

            // JedisPoolConfig config = new JedisPoolConfig();
            // config.set...

            String port = properties.getProperty("redis.port", "6379");
            String host = properties.getProperty("redis.host", "localhost");

            jedisPool = new JedisPool(host, Integer.valueOf(port));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JedisGeoClient.getInstance().geoAdd("citys:location", 121.48941, 31.40527, "shanghai");

        GeoCoordinate geoCoordinate = JedisGeoClient.getInstance().geoPos("citys:location", "shanghai");

        System.out.println(geoCoordinate.getLongitude() + "-------------" + geoCoordinate.getLatitude());
    }

    @Override
    protected void run() {
        // do something
    }
}
