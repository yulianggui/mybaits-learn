package com.zhegui.myorm.v2.plugin;

import org.apache.ibatis.plugin.Interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *  插件调用链
 *    configuration 持有
 *    在容器中只有一个
 *
 *    ZGInterceptorChain 中持有所有配置进来的ZGInterceptor 拦截器
 *       调用pluginAll，对target进行链路代理（增强）
 *         即interceptors有多少个满足的，就进行多少次代理
 *            代理对象又被代理了，所有是代理链
 * create by zhegui on 2018/11/18
 */
public class ZGInterceptorChain {

    private List<ZGInterceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target){
        //使用ZGPlugin对target进行代理   interceptors 有几个就代理几次
        Iterator<ZGInterceptor> iterator = interceptors.iterator();
        while(iterator.hasNext()){
            ZGInterceptor item = iterator.next();
            target = item.plugin(target);
        }
        return target;
    }

    public void addInterceptor(ZGInterceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    public List<ZGInterceptor> getInterceptors() {
        return Collections.unmodifiableList(this.interceptors);
    }
}
