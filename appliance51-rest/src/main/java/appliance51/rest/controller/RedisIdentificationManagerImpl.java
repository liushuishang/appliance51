//package appliance51.rest.controller;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.exceptions.JedisException;
//
///**
// * 一个用来测试用的IdentificationManager实现
// * @author
// * @see
// * @since 1.0
// */
//public class RedisIdentificationManagerImpl implements IdentificationManager
//{
//    public static Logger logger = LoggerFactory.getLogger(RedisIdentificationManagerImpl.class);
//
//    protected JedisPool jedisPool;
//
//    public void setJedisPool(JedisPool jedisPool)
//    {
//        this.jedisPool = jedisPool;
//    }
//
//    protected Jedis getJedis() throws JedisException
//    {
//        Jedis jedis = null;
//        try
//        {
//            jedis = jedisPool.getResource();
//        }
//        catch (JedisException e)
//        {
//            logger.error("failed:jedisPool getResource.", e);
//            if (jedis != null)
//            {
//                jedisPool.returnBrokenResource(jedis);
//            }
//            throw e;
//        }
//        return jedis;
//    }
//
//    protected void release(Jedis jedis, boolean isBroken)
//    {
//        if (jedis != null)
//        {
//            if (isBroken)
//            {
//                jedisPool.returnBrokenResource(jedis);
//            }
//            else
//            {
//                jedisPool.returnResource(jedis);
//            }
//        }
//    }
//
//    protected String addStringToJedis(String key, String value, int cacheSeconds)
//    {
//        Jedis jedis = null;
//        boolean isBroken = false;
//        String lastVal = null;
//        try
//        {
//            jedis = this.getJedis();
//
//            lastVal = jedis.getSet(key, value);
//            if (cacheSeconds != 0)
//            {
//                jedis.expire(key, cacheSeconds);
//            }
//            logger.debug("succeed key:{} value:{}", new Object[]
//            { key, value });
//        }
//        catch (Exception e)
//        {
//            isBroken = true;
//            logger.error("failed key:{} value:{}", new Object[]
//            { key, value });
//        }
//        finally
//        {
//            release(jedis, isBroken);
//        }
//        return lastVal;
//    }
//
//    public String getStringFromJedis(String key)
//    {
//        String value = null;
//        Jedis jedis = null;
//        boolean isBroken = false;
//        try
//        {
//            jedis = this.getJedis();
//            if (jedis.exists(key))
//            {
//                value = jedis.get(key);
//                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
//                logger.debug("succeed key:{} value:{}", new Object[]
//                { key, value });
//            }
//        }
//        catch (Exception e)
//        {
//            isBroken = true;
//            logger.error("failed key:{} value:{}", new Object[]
//            { key, value }, e);
//        }
//        finally
//        {
//            release(jedis, isBroken);
//        }
//        return value;
//    }
//
//    @Override
//    public void putIdentification(String key, String ticket, long time, int expires)
//    {
//        addStringToJedis(key, ticket, expires / 1000);
//        addStringToJedis(ticket, String.valueOf(time) + "," + key, expires / 1000);
//    }
//
//    @Override
//    public String getTicket(String key)
//    {
//        return getStringFromJedis(key);
//    }
//
//    @Override
//    public long getTicketTime(String ticket)
//    {
//        try
//        {
//            String time_key = getStringFromJedis(ticket);
//            return time_key != null ? Long.parseLong(time_key.substring(0, time_key.lastIndexOf(','))) : 0;
//        }
//        catch (Exception e)
//        {
//            return 0;
//        }
//    }
//
//    @Override
//    public String getKey(String ticket)
//    {
//        try
//        {
//            String time_key = getStringFromJedis(ticket);
//            return time_key != null ? time_key.substring(time_key.lastIndexOf(',') + 1) : null;
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//    }
//
//}
