package hello;

import java.util.Arrays;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import argo.jdom.JsonNode;
import argo.jdom.JdomParser;
import argo.saj.InvalidSyntaxException;



public class PoolManager {
  private static PoolManager instance = null;
  private JedisPool pool = null;

  PoolManager() {
    try {
      String vcap_services = System.getenv("VCAP_SERVICES");
      System.out.println("VCAP_SERVICES = "+vcap_services);
      if (vcap_services != null && vcap_services.length() > 0) {

        //parsing rediscloud credentials
        JsonNode root = new JdomParser().parse(vcap_services);
        JsonNode redisCloudNode = root.getNode("redisCloud");
        JsonNode credentials = root.getNode("credentials");

        pool = new JedisPool(new JedisPoolConfig(),
                credentials.getStringValue("hostname"),
                Integer.parseInt(credentials.getNumberValue("port")),
                Protocol.DEFAULT_TIMEOUT,
                credentials.getStringValue("password"));
      }
    } catch (InvalidSyntaxException ex) {
      //  need to log this exception ... the next thing to figure out.
    }
  }


  public JedisPool getPool() {
    return pool;
  }

}
