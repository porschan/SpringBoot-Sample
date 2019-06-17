package com.chanchifeng.dbexpand;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * 参考https://blog.csdn.net/qq_35387940/article/details/82625922
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheManager rcm=RedisCacheManager.create(connectionFactory);

        return rcm;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
                Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //序列化设置 ，这样计算是正常显示的数据，也能正常存储和获取
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    // @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//
//        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // template.setKeySerializer(redisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//
//        return template;
//    }

    /**
     * 旧配置
     *     @Bean
     *     public KeyGenerator simpleKey(){
     *         return new KeyGenerator() {
     *             @Override
     *             public Object generate(Object target, Method method, Object... params) {
     *                 StringBuilder sb = new StringBuilder();
     *                 sb.append(target.getClass().getName()+":");
     *                 for (Object obj : params) {
     *                     sb.append(obj.toString());
     *                 }
     *                 return sb.toString();
     *             }
     *         };
     *     }
     *
     *     @Bean
     *     public KeyGenerator objectId(){
     *         return new KeyGenerator() {
     *             @Override
     *             public Object generate(Object target, Method method, Object... params){
     *                 StringBuilder sb = new StringBuilder();
     *                 sb.append(target.getClass().getName()+":");
     *                 try {
     *                     sb.append(params[0].getClass().getMethod("getId", null).invoke(params[0], null).toString());
     *                 }catch (NoSuchMethodException no){
     *                     no.printStackTrace();
     *                 }catch(IllegalAccessException il){
     *                     il.printStackTrace();
     *                 }catch(InvocationTargetException iv){
     *                     iv.printStackTrace();
     *                 }
     *                 return sb.toString();
     *             }
     *         };
     *     }
     *
     *     @Bean
     *     public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
     *         RedisCacheManager manager = new RedisCacheManager(redisTemplate);
     *         manager.setDefaultExpiration(30);//30秒
     *         manager.setUsePrefix(true);
     *         return manager;
     *     }
     *
     *     @Bean
     *     public RedisTemplate<String, String> redisTemplate(
     *             RedisConnectionFactory factory) {
     *         StringRedisTemplate template = new StringRedisTemplate(factory);
     *         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
     *         ObjectMapper om = new ObjectMapper();
     *         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     *         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     *         jackson2JsonRedisSerializer.setObjectMapper(om);
     *         template.setValueSerializer(jackson2JsonRedisSerializer);
     *         template.afterPropertiesSet();
     *         return template;
     *     }
     */

}
