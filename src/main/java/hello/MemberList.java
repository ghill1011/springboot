package hello;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MemberList {
  private String memberlist;
  private String partner;

  public MemberList() {
    // get a connection to persistence
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // set the account list
    jedis.lpush("BankOfAmerica", "1234567890123456789");
    jedis.lpush("BankOfAmerica", "9123456789012345678");
    jedis.lpush("BankOfAmerica", "8912345678901234567");
    jedis.lpush("BankOfAmerica", "7891234567890123456");
    jedis.lpush("BankOfAmerica", "6789123456789012345");
    jedis.lpush("BankOfAmerica", "5678912345678901234");
    jedis.lpush("BankOfAmerica", "4567891234567890123");

    jedis.lpush("Citibank", "1234567890123456789");
    jedis.lpush("Citibank", "9123456789012345678");
    jedis.lpush("Citibank", "8912345678901234567");
    jedis.lpush("Citibank", "7891234567890123456");

    // release the persistence connection
    poolManager.close();
  }

  public MemberList(String partner) {

    // get a connection to persistence
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // get the account list
    List<String> list = jedis.lrange(partner+".accounts", 0, jedis.llen(partner+".accounts"));
    String ml = "[";
    for (String item:list) {
      ml += item+",";
    }
    ml = ml.replaceAll(",+$",""); //trim trailing ","
    ml +="]";
    setMemberList(ml);

    // release the persistence connection
    poolManager.close();
  }

  public void setMemberList(String memberlist) {
    this.memberlist = memberlist;
  }
  public String getMemberList() {
    return this.memberlist;
  }
  public void setPartner(String partner) {
    this.partner = partner;
  }
  public String getPartner() {
    return this.partner;
  }

}
