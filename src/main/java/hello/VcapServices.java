package hello;

import com.google.gson.annotations.SerializedName;

public class VcapServices {

  public RedisCloud getRedisCloud() {
    return rediscloud;
  }
  public void setRedisCloud(RedisCloud rediscloud) {
    this.rediscloud = rediscloud;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("RedisCloud: "+getRedisCloud()+"\n");
    return sb.toString();
  }
}
