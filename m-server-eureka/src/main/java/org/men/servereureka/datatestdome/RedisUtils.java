package org.men.servereureka.datatestdome;

import redis.clients.jedis.Jedis;

public class RedisUtils {

    public static Boolean setnx(String key,String val){

        Jedis jedis = new Jedis("39.100.150.21", 30005);
        jedis.auth("YJF#33bbe31d51b3b60370dc5%2106");
        String ping = jedis.ping();
        System.out.println(ping);
        return null;
    }

    public static void main(String[] args) {
        setnx("","");
    }
}
