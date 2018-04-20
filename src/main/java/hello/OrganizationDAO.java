package hello;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class OrganizationDAO {

  public static List<String> getAll() {
    // get a connection to jedis
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // get the list of organizations
    List<String> list = jedis.lrange("organizations", 0, -1);

    // release the jedis connection
    poolManager.close();
    return list;
  }

  public void insert(String organization) {
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    jedis.lpush("ogranizations", organization);

    poolManager.close();
    retur
  }

  public void delete(String organization) {
    PoolManager poolManager = new PoolManager();
    Jedis jedis = poolManager.getJedis();

    // remove the members of the organization and the organization
    jedis.del(organization+".members");
    jedis.lrem("ogranizations", 0, organization);

    poolManager.close();
  }

}
