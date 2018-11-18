package com.zhegui.myorm.v2.plugin;

import com.zhegui.myorm.v2.bind.MapperRegister;
import com.zhegui.myorm.v2.executor.IExecutor;
import com.zhegui.myorm.v2.executor.SimpleExecutor;
import com.zhegui.myorm.v2.statement.StatementHandle;
import sun.plugin2.main.server.Plugin;

/**
 * create by zhegui on 2018/11/18
 */
@ZGIntercepts(
        @ZGSignature(type = IExecutor.class, method = "selectOne",
        args = {MapperRegister.MapperDate.class, Object.class})
)
public class TestZGPlugin implements ZGInterceptor{
    @Override
    public Object intercept(ZGInvocation invocation) throws Throwable {
        System.out.println("来了， TestZGPlugin， 配置了 TestZGPlugin --> IExecutor.class 的 selectOne 方法！！！！！！");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return ZGPlugin.wrap(target, this);
    }
}
