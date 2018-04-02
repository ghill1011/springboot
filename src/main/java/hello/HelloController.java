package hello;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Welcome to Gregg's Points Bank";
  }

  @RequestMapping("/members/{partner}")
  public Account[] getmembers(@PathVariable String partner) {//Rest Endpoint
    MemberList ml = new MemberList();
    return ml;
  }

  @RequestMapping("/seedmembers/{partner}")
  public Account[] seedmembers(@PathVariable String partner) {//Rest Endpoint
    MemberList ml = new MemberList();
    return ml;
  }
}
