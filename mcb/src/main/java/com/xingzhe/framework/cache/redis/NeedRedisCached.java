package com.xingzhe.framework.cache.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedRedisCached
{
    /**
     * 1、STRING 2、LIST 3、HASH 4、SET 5、SORTED_SET
     * 
     * @return
     */
    String type() default "STRING";
    /**
     * 返回类型是否为数组
     * */
    boolean isArray() default false;
    Class<?> returnType() default Object.class;
    
    int[] keyArgs() default {};
    
    /**
     * HASHMAP 使用时 preKey 默认为 COMMON 当 preKey == COMMON 时则 endKey 不得为空
     * 
     * 否则 endKey 可以为空 可以根绝 参数的值获取 keyArgs 组成 map的第二个参数
     * 
     * @return
     */
    String preKey() default "COMMON";
    
    String endKey() default "";
    
    /**
     * 设置失效时间
     * @return
     */
    int time() default -1;
    
}
