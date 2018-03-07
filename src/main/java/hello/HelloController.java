package hello;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {


    String message = null;
    JedisPool pool = PoolManager.getInstance().getPool();
    if (pool == null)  return "pool not set";
    Jedis jedis = pool.getResource();
    if (jedis == null)  return "jedis not set";
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    pool.returnResource(jedis);
    pool.close();
    return "Greetings from Gregg's first Spring Boot app running in PCF!\n"+value;
  }
}
