package com.kai.redis.common;

import com.sun.tools.corba.se.idl.ExceptionEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ggk
 * @date 2019/5/29 0029 下午 6:23
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key,long time){
        try{
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 获取缓存的失效时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key值是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 传入一个或多个
     */
    public void del(String...key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
  //================================String===========================================
    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key
     * @param o
     * @return
     */
    public boolean set(String key,Object o){
        try{
            redisTemplate.opsForValue().set(key,o);
            return true;
        }catch(Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key
     * @param o
     * @param time
     * @return
     */
    public boolean set(String key,Object o,long time){
        try{
            if (time>0){
                redisTemplate.opsForValue().set(key, o, time,TimeUnit.SECONDS);
            }else{
                set(key, o);
            }
            return true;
        }catch(Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public  long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long decr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //=================================Map=========================================

    /**
     * hashGet
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取hashKey对应的所有key-value
     * @param key
     * @return
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键值
     * @param map  键值对
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch(Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * HashSet 并设置过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果数据不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key,String item,Object value){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 像一张hash表中放入数据，并设置其过期时间
     * @param key
     * @param item
     * @param value
     * @param time  注：如果以前存在过期时间则覆盖此时间
     * @return
     */
    public boolean hset(String key,String item,Object value,long time){
        try{
            redisTemplate.opsForHash().put(key,item,value);
            if(time >0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key
     * @param item 可以使用多个 但不能为空
     */
    public void hdel(String key,Object...item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     *判断hash表中是否有该项的值
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     *hash 递增 如果不存在，就会创建一个 并把新增后的值返回
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hincr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,by);
    }

    /**
     * hash 递减
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hdecr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    //============================set====================================

    /**
     * 根据key获取对应的set值
     * @param key
     * @return
     */
    public Set<Object> sGet(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch(Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * 根据value 查看set中是否存在
     * @param key
     * @param value
     * @return
     */
    public boolean sHasKey(String key,Object value){
        try{
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将数据放入缓存中
     * @param key
     * @param value
     * @return
     */
    public long sSet(String key,Object... value){
        try{
            return redisTemplate.opsForSet().add(key, value);
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

    /**
     * 将set数据放入缓存中
     * @param key
     * @param time
     * @param value
     * @return
     */
    public long sSet(String key,long time,Object... value){
        try{
            long count=redisTemplate.opsForSet().add(key,value);
            if(time>0){
                expire(key, time);
            }
            return count;
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key
     * @return
     */
    public long sGetSetSize(String key){
        try{
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

    /**
     * 移除值为value
     * @param key
     * @param values
     * @return
     */
    public long setRemove(String key,Object... values){
        try{
            long count=redisTemplate.opsForSet().remove(key, values) ;
            return count;
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

    //=======================list===============================

    /**
     * 获得list缓存的长度
     * @param key
     * @return
     */
    public  long lGetListSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }

    /**
     * 通过索引获取list中的值
     * @param key
     * @param index
     * @return
     */
    public Object lGetIndex(String key,long index){
        try{
            return redisTemplate.opsForList().index(key,index);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * 将list放入缓存中
     * @param key
     * @param value
     * @return
     */
    public boolean lSet(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将list 放入缓存中并设置其失效时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean lSet(String key,Object value,long time){
        try{
            redisTemplate.opsForList().rightPush(key, value);
            if(time>0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将list 放入缓存中
     * @param key
     * @param value
     * @return
     */
    public boolean lSet(String key, List<Object> value){
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 将list放入缓存中 并设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean lSet(String key,List<Object> value,long time){
        try{
            redisTemplate.opsForList().rightPushAll(key,value);
            if(time >0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean lUpdateIndex(String key,long index,Object value){
        try{
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    /**
     * 移除N个值为value的缓存
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long lRemove(String key,long count,Object value){
        try{
            long remove=redisTemplate.opsForList().remove(key,count,value);
            return remove;
        }catch (Exception e){
            log.info(e.getMessage());
            return 0;
        }
    }
}