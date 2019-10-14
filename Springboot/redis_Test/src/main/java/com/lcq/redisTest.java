package com.lcq;

import redis.clients.jedis.Jedis;

public class redisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.159.141",6379);
        String name = jedis.get("name");
        System.out.println("拿到的值为：" + name);
         jedis.set("name2", "连接上了");
        Long name1 = jedis.ttl("name");
        System.out.println("name 的生存时间为" + name1);
       jedis.expire("name2",10);
        System.out.println("name2的剩余时间" + jedis.ttl("name2"));
        System.out.println("name2的生存时间为：" + jedis.persist("name2"));
    }
}
