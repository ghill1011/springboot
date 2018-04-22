package hello;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MemberDAO {
  public static final String key = "members";

  public MemberList(String partner) {
    this.partner = partner;

    // get a connection to persistence
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // get the account list
    List<String> list = jedis.lrange(partner+".members", 0, jedis.llen(partner+".members"));
    String ml = "[";
    for (String item:list) {
      ml += item+",";
    }
    ml = ml.replaceAll(",+$",""); //trim trailing ","
    ml +="]";
    setMemberList(ml);
    System.out.println(ml);

    // release the persistence connection
    poolManager.close();
  }

  public static List<String> getAll(organization) {
    // get a connection to jedis
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // get the list of organizations
    List<String> list = jedis.lrange(organization+"."+key, 0, -1);

    // release the jedis connection
    poolManager.close();
    return list;
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
