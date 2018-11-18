package com.zhegui.myorm.v2.plugin;

import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.executor.IExecutor;
import sun.plugin2.main.server.Plugin;

/**
 * create by zhegui on 2018/11/18
 */
@ZGIntercepts(
        @ZGSignature(
                type = IExecutor.class,
                method = "selectOne",
                args = {MapperRegister.MapperDate.class, Object.class}
        )
)
public class TwoZGPlugin implements ZGInterceptor{
    @Override
    public Object intercept(ZGInvocation invocation) throws Throwable {
        System.out.println("又要代理了， TwoZGPlugin -------》  IExecutor.class 的 selectOne 方法！");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return ZGPlugin.wrap(target, this);
    }
}
