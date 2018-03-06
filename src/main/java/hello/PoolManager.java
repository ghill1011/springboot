package hello;

import java.util.Arrays;
import redis.clients.jedis.Jedis;

public class PoolManager {
  private JedisPool pool = null;

  protected PoolManager() {
    try {
      String vcap_services = System.getenv("VCAP_SERVICES");
      if (vcap_services != null && vcap_servcies.length() > 0) {

        //parsing rediscloud credentials
        JsonRootNode root = new JdomParser().parse(vcap_services);
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

  public static PoolManager getInstance() {
    if (instance == null) {
      System.out.println("creating new connection manager");
      instance = new PoolManager();
    }
    return instance;
  }

  public JedisPool getPool() {
    return pool;
  }

}
