package com.wallxu.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/17.
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    // Redis服务器IP
    private static String ip;

    // Redis的端口号
    private static int port;

    // 访问密码
    private static String password;

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int max_active;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int max_idle;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int max_wait;

    // 超时时间
    private static int timeout;


    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        RedisConfig.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        RedisConfig.port = port;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        RedisConfig.password = password;
    }

    public static int getMax_active() {
        return max_active;
    }

    public static void setMax_active(int max_active) {
        RedisConfig.max_active = max_active;
    }

    public static int getMax_idle() {
        return max_idle;
    }

    public static void setMax_idle(int max_idle) {
        RedisConfig.max_idle = max_idle;
    }

    public static int getMax_wait() {
        return max_wait;
    }

    public static void setMax_wait(int max_wait) {
        RedisConfig.max_wait = max_wait;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        RedisConfig.timeout = timeout;
    }
}
