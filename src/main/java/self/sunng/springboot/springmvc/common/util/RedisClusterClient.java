package self.sunng.springboot.springmvc.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import self.sunng.springboot.springmvc.common.Constant;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class RedisClusterClient {

    private static JedisCluster JEDIS_CLUSTER = null;
    private static final int RETRY_TIMES = 3;

    static {
    	init(null, CommonUtil.getProperty("redis.cluster.nodes"));
    }

    private static void init(JedisPoolConfig config, String redisClusterNodes) {
        if (config == null) {
            config = defaultConfig();
        }
        Set<HostAndPort> jedisClusterNodeSet = covert2redisClusterNodeSet(redisClusterNodes);
        JEDIS_CLUSTER = new JedisCluster(jedisClusterNodeSet, config);

    }


    private static Set<HostAndPort> covert2redisClusterNodeSet(String jedisClusterNodes) {
        Validate.notEmpty(jedisClusterNodes);
        Set<HostAndPort> jedisClusterNodeSet = new HashSet<>();
        String[] nodeArray = jedisClusterNodes.split(Constant.STR_COMMA);
        for (String node : nodeArray) {
            String[] nodeInfoArray = node.split(Constant.STR_COLON);
            String host = nodeInfoArray[0];
            String port = nodeInfoArray[1];
            jedisClusterNodeSet.add(new HostAndPort(host, Integer.valueOf(port)));
        }
        return jedisClusterNodeSet;
    }

    private static JedisPoolConfig defaultConfig() {

        JedisPoolConfig config = new JedisPoolConfig();
        //连接耗尽时是否阻塞, false报异常, true阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);

        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");

        //是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);

        //MBean ObjectName = new ObjectName("org.apache.commons.pool2:type=GenericObjectPool,name=" + "pool" + i); 默 认为"pool", JMX不熟,具体不知道是干啥的...默认就好.
        config.setJmxNamePrefix("pool");

        //是否启用后进先出, 默认true
        config.setLifo(true);

        //最大空闲连接数, 默认8个
        config.setMaxIdle(10);

        //最大连接数, 默认8个
        config.setMaxTotal(10);

        //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setMaxWaitMillis(-1);

        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        config.setMinEvictableIdleTimeMillis(1800000);

        //最小空闲连接数, 默认0
        config.setMinIdle(0);

        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(false);

        //在空闲时检查有效性, 默认false
        config.setTestWhileIdle(false);

        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(-1);

        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(3);

        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);

        return config;

    }


    public static void close() {
        try {
            JEDIS_CLUSTER.close();
            log.debug("JEDIS_CLUSTER has be closed .");
        } catch (IOException e) {
            log.debug("closed JEDIS_CLUSTER exception.", e);
        }
    }


    public static String set(String key, String value) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.set(key, value);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("set fail: [{}:{}] .", key, value);
            }
        }
        return null;
    }


    public static String get(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.get(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("GET [{}] fail.", key);

            }
        }
        return null;
    }


    public static Boolean exists(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.exists(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("exists [{}] fail.", key);

            }
        }
        return null;
    }


    public static String type(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.type(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("type [{}] fail.", key);

            }
        }

        return null;
    }


    public static Long expire(String key, int seconds) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.expire(key, seconds);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("expire [{}:{}] fail.", key, seconds);

            }
        }
        return null;
    }


    public static Long expireAt(String key, long unixTime) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.expireAt(key, unixTime);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("expireAt [{}][{}] fail.", key, unixTime);

            }
        }
        return null;
    }

    public static Long del(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.del(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("del [{}] fail.", key);

            }
        }
        return null;
    }
    
    public static Long rpush(String key, String... string) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.rpush(key, string);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("rpush [{}:{}] fail.", key, StringUtils.arrayToCommaDelimitedString(string));

            }
        }
        return null;
    }
    
    public static List<String> lrange(String key, long start, long end) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.lrange(key, start, end);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("lrange [{}][{}->{}] fail.", key, start, end);

            }
        }
        return null;
    }
    
    public static Long llen(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.llen(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("llen [{}] fail.", key);

            }
        }
        return null;
    }
    
    public static String lpop(String key) {
        for (int i = 0; i < RETRY_TIMES; i++) {
            try {
                return JEDIS_CLUSTER.lpop(key);
            } catch (JedisConnectionException e) {
                log.error("", e);
            } catch (Exception e) {
                log.error("lpop [{}] fail.", key);

            }
        }
        return null;
    }

}
