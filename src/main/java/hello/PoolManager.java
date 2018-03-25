package hello;

import java.util.Arrays;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class PoolManager {
  private static PoolManager instance = null;
  private JedisPool pool = null;

  PoolManager() {
  //try {
      String vcap_services = System.getenv("VCAP_SERVICES");
      if (vcap_services != null && vcap_services.length() > 0) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        RedisCloud rediscloud = gson.fromJson(vcap_services, RedisCloud.class);
        System.out.println(rediscloud);

        pool = new JedisPool(new JedisPoolConfig(),
                rediscloud.getCredentials().getHostName(),
                Integer.parseInt(rediscloud.getCredentials().getPort()),
                Protocol.DEFAULT_TIMEOUT,
                rediscloud.getCredentials().getPassword());

      }
    /*} catch (InvalidSyntaxException ex) {
      //  need to log this exception ... the next thing to figure out.
    }*/
  }


  public JedisPool getPool() {
    return pool;
  }

}
