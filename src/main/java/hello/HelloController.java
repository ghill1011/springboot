package hello;

import redis.clients.jedis.Jedis;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {

    JedisPool pool = PoolManager.getInstance().getPool();
    Jedis jedis = pool.getResource();
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    pool.returnResource(jedis);
    return "Greetings from Gregg's first Spring Boot app running in PCF!\n"+value;
  }
}
