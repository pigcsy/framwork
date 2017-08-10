package com.common.constant;

import com.core.exception.GatewayException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * redis中key, field, expire模板
 *
 * @author csy
 * @date 2017/3/13 上午11:00
 */
@Repository
public class RedisKeyTemplate {

    /**
     * 验证码在redis中的key和过期时间 <br/>
     * YXT_SMS_CODE_{}_TYPE_{} => 调用 getRedisKey 将得到类似于 {{YXT_SMS_CODE_15658896952_TYPE_1}} 的key [redis中的key]
     * 900 => 设置过期时间为900秒
     * <p>
     * {@link RedisKeyConstBuilder}
     */
    public static final RedisKeyConstBuilder DEMO_SMS_CODE = RedisKeyConstBuilder.create("YDEMO_SMS_CODE_{}_TYPE_{}", 900);

    /**
     * hash中存储 <br/>
     * DEMO_CAR_BRAND => 为hash中的key
     * {} => 为hash中的field
     * 车辆信息[哈希]
     */
    public static final RedisKeyConstBuilder DEMO_CAR_BRAND = RedisKeyConstBuilder.create("DEMO_CAR_BRAND", "{}");

    /**
     * 权限资源数据
     */
    public static final RedisKeyConstBuilder AUTHORIZATION_METADATA_SOURCE = RedisKeyConstBuilder.create("AUTHORIZATION_METADATA_SOURCE", -1);


    /**
     *
     */
    public static class RedisKeyConstBuilder implements Serializable {
        private static final long serialVersionUID = -788058748951890721L;
        /**
         * 缓存中的key
         */
        private String key;

        /**
         * hashmap中key的field
         */
        private String field;

        /**
         * 缓存中key对应的过期时间
         */
        private int expire;

        public RedisKeyConstBuilder() {
        }

        public RedisKeyConstBuilder(String key, int expire) {
            this.key = key;
            this.expire = expire;
        }

        public RedisKeyConstBuilder(String key, String field) {
            this.key = key;
            this.field = field;
        }

        public static RedisKeyConstBuilder create() {
            return new RedisKeyConstBuilder();
        }

        public static RedisKeyConstBuilder create(String key, int expire) {
            return new RedisKeyConstBuilder(key, expire);
        }

        public static RedisKeyConstBuilder create(String key, String field) {
            return new RedisKeyConstBuilder(key, field);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getRedisKey(String... replaceStr) {
            return replaceConstStr(getKey(), replaceStr);
        }

        public String getRedisField(String... replaceStr) {
            return replaceConstStr(getField(), replaceStr);
        }

        public String getFuzzyRedisKey(String... replaceStr) {
            String result = getKey();
            int index = 0;
            while (result.indexOf("{}") >= 0) {
                if (index >= replaceStr.length) {
                    result = StringUtils.replaceOnce(result, "{}", "*");
                    index++;
                    continue;
                }
                result = StringUtils.replaceOnce(result, "{}", replaceStr[index]);
                index++;
            }
            return result;
        }

        private String replaceConstStr(String string, String... replaceStr) {
            if (string == null)
                return null;
            String result = string;
            int index = 0;
            while (result.indexOf("{}") >= 0) {
                if (ArrayUtils.isEmpty(replaceStr) || replaceStr.length < index) {
                    throw new GatewayException("缓存参数错误", "-1");
                }
                result = StringUtils.replaceOnce(result, "{}", replaceStr[index]);
                index++;
            }
            return result;
        }
    }
}
